package com.uguraltintas.rickandmorty.model.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.uguraltintas.rickandmorty.model.characters.CharacterModel
import com.uguraltintas.rickandmorty.model.episodes.EpisodeModel

data class EpisodeWithCharacters(
    @Embedded val episode: EpisodeModel,
    @Relation(
        parentColumn = "episodeUrl",
        entity = CharacterModel::class,
        entityColumn = "characterUrl",
        associateBy = Junction(
            value = CharacterAndEpisode::class,
            parentColumn = "episodeUrlMap",
            entityColumn = "characterUrlMap"
        )
    )
    val characterList: List<CharacterModel>
)