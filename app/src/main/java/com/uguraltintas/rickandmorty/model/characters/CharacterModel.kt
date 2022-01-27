package com.uguraltintas.rickandmorty.model.characters

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "character_table")
data class CharacterModel(
    val created: String?,
    val episode: List<String>?,
    val gender: String?,
    val id: Int?,
    val image: String?,
    @Embedded
    val location: Location?,
    val name: String?,
    @Embedded
    val origin: Origin?,
    val species: String?,
    val status: String?,
    val type: String?,
    @PrimaryKey
    @SerializedName("url")
    val characterUrl: String
)