package com.uguraltintas.rickandmorty.ui.episodes.detail

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
class EpisodeDetailViewModel @Inject constructor(private val repository: RickAndMortyRepository): ViewModel() {

    private val _episode = MutableLiveData<EpisodeModel>()
    val episode : LiveData<EpisodeModel>
    get() = _episode

    fun getEpisodeById(id : Int) {
        GlobalScope.launch {
            val episodeByID = repository.getEpisodeById(id)
            _episode.postValue(episodeByID)
        }
    }
}