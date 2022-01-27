package com.uguraltintas.rickandmorty.model.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.uguraltintas.rickandmorty.model.characters.CharacterModel
import com.uguraltintas.rickandmorty.model.episodes.EpisodeModel

data class CharacterWithEpisodes(
    @Embedded val character: CharacterModel,
    @Relation(
        parentColumn = "characterUrl",
        entity = EpisodeModel::class,
        entityColumn = "episodeUrl",
        associateBy = Junction(
            value = CharacterAndEpisode::class,
            parentColumn = "characterUrlMap",
            entityColumn = "episodeUrlMap"
        )
    )
    val episodeList: List<EpisodeModel>
)