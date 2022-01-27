package com.uguraltintas.rickandmorty.model.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import com.uguraltintas.rickandmorty.model.characters.CharacterModel
import com.uguraltintas.rickandmorty.model.episodes.EpisodeModel

@Entity(
    primaryKeys = ["characterUrlMap", "episodeUrlMap"],
    foreignKeys = [
        /* Rule that says the value of the characterUrlMap column MUST be a value
            that exists in the characterUrl column of one of the rows in the character_table table
         */
        ForeignKey(
            entity = CharacterModel::class,
            parentColumns = ["characterUrl"],
            childColumns = ["characterUrlMap"],
            /* Option within foreign key but helpful */
            onDelete = CASCADE, // if parent is deleted then all children (rows in this table) are deleted rather than orphaned
            onUpdate = CASCADE // if characterUrl column is changed then all the children in this table with the parents value are changed
        ),
        ForeignKey(
            entity = EpisodeModel::class,
            parentColumns = ["episodeUrl"],
            childColumns = ["episodeUrlMap"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
    ]
)
data class CharacterAndEpisode(
    val characterUrlMap: String,
    @ColumnInfo(index = true)
    val episodeUrlMap: String
)