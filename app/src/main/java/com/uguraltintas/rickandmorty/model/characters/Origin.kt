package com.uguraltintas.rickandmorty.model.characters

import androidx.room.ColumnInfo


data class Origin(
    @ColumnInfo(name = "origin_name") val name: String?,
    @ColumnInfo(name = "origin_url") val url: String?
)