package com.uguraltintas.rickandmorty.ui.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uguraltintas.rickandmorty.model.characters.CharacterModel
import com.uguraltintas.rickandmorty.model.relations.CharacterWithEpisodes
import com.uguraltintas.rickandmorty.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class CharactersViewModel @Inject constructor(private val repository: RickAndMortyRepository) :
    ViewModel() {
    private val _characterList = MutableLiveData<Pair<List<Int?>, List<CharacterModel>?>>()
    val characterList: LiveData<Pair<List<Int?>, List<CharacterModel>?>>
        get() = _characterList

    fun getCharacterList(page: Int) {

        GlobalScope.launch {
            val characterListFromDB: List<CharacterModel> = repository.getCharacterListFromDB()

            if (characterListFromDB.isNotEmpty()) {
                Log.e(
                    "CharacterId",
                    characterListFromDB[characterListFromDB.size - 1].id!!.toString()
                )
                if (characterListFromDB[characterListFromDB.size - 1].id!! >= page * 20) {
                    val pair = Pair(listOf(characterListFromDB.size, 1), characterListFromDB)
                    _characterList.postValue(pair)
                } else {
                    getListFromService(page)
                }
            } else {
                getListFromService(page)
            }
        }
    }

    fun insertCharacter(character: CharacterModel) {
        GlobalScope.launch {
            repository.insertCharacter(character)
        }
    }

    fun getCharacterWithEpisodes(): List<CharacterWithEpisodes> {
        lateinit var list: List<CharacterWithEpisodes>
        GlobalScope.launch {
            list = repository.getCharacterWithEpisodes()
        }
        return list
    }

    private fun getListFromService(page: Int) {
        GlobalScope.launch {
            val response = repository.getCharacterListFromService(page)
            if (response.isSuccessful) {
                val pair =
                    Pair(listOf(response.body()?.info?.pages, 0), response.body()?.results)
                _characterList.postValue(pair)
            }
        }

    }


}