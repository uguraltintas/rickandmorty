package com.uguraltintas.rickandmorty.model.locations

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location_table")
data class LocationModel(
    val created: String?,
    val dimension: String?,
    val id: Int?,
    val name: String?,
    val residents: List<String>?,
    val type: String?,
    @PrimaryKey
    val url: String
)