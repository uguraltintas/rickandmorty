package com.uguraltintas.rickandmorty.ui.characters.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uguraltintas.rickandmorty.model.characters.CharacterModel
import com.uguraltintas.rickandmorty.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val repository: RickAndMortyRepository) :
    ViewModel() {

    private val _character = MutableLiveData<CharacterModel>()
    val character: LiveData<CharacterModel>
        get() = _character

    fun getCharacterById(id: Int) {
        GlobalScope.launch {
            val character = repository.getCharacterById(id)
            _character.postValue(character)
        }
    }

}