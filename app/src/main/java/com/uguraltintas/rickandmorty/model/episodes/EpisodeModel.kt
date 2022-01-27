package com.uguraltintas.rickandmorty.model.episodes


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "episode_table")
data class EpisodeModel(
    @SerializedName("air_date")
    val airDate: String?,
    val characters: List<String>?,
    val created: String?,
    val episode: String?,
    val id: Int?,
    val name: String?,
    @PrimaryKey
    @SerializedName("url")
    val episodeUrl: String
)