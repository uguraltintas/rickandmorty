package com.uguraltintas.rickandmorty.model.characters


import com.uguraltintas.rickandmorty.model.Info

data class Characters(
    val info: Info?,
    val results: List<CharacterModel>?
)