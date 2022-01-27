package com.uguraltintas.rickandmorty.model.episodes

import com.uguraltintas.rickandmorty.model.Info


data class Episodes(
    val info: Info?,
    val results: List<EpisodeModel>?
)