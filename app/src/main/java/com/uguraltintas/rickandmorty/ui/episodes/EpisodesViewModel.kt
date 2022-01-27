package com.uguraltintas.rickandmorty.ui.episodes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uguraltintas.rickandmorty.model.episodes.EpisodeModel
import com.uguraltintas.rickandmorty.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class EpisodesViewModel @Inject constructor(private val repository: RickAndMortyRepository) :
    ViewModel() {

    private val _episodeList = MutableLiveData<Pair<List<Int?>, List<EpisodeModel>?>>()
    val episodeList: LiveData<Pair<List<Int?>, List<EpisodeModel>?>>
        get() = _episodeList

    fun getEpisodeList(page: Int) {
        GlobalScope.launch {
            val episodeListFromDB: List<EpisodeModel> = repository.getEpisodeListFromDB()

            if (episodeListFromDB.isNotEmpty()) {
                Log.e("EpisodeId", episodeListFromDB[episodeListFromDB.size - 1].id!!.toString())
                if (episodeListFromDB[episodeListFromDB.size - 1].id!! >= page * 20) {

                    val pair = Pair(listOf(episodeListFromDB.size, 1), episodeListFromDB)
                    _episodeList.postValue(pair)
                } else {
                    getListFromService(page)
                }
            } else {
                getListFromService(page)
            }
        }
    }

    fun insertEpisode(episode: EpisodeModel) {
        GlobalScope.launch {
            repository.insertEpisode(episode)
        }
    }

    private fun getListFromService(page: Int) {
        GlobalScope.launch {
            val response = repository.getEpisodeListFromService(page)
            if (response.isSuccessful) {
                val pair =
                    Pair(listOf(response.body()?.info?.pages, 0), response.body()?.results)
                _episodeList.postValue(pair)
            }
        }
    }
}