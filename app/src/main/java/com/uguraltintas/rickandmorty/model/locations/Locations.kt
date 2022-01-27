package com.uguraltintas.rickandmorty.model.locations

import com.uguraltintas.rickandmorty.model.Info


data class Locations(
    val info: Info?,
    val results: List<LocationModel>?
)