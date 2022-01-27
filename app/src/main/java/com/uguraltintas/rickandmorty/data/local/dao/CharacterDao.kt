package com.uguraltintas.rickandmorty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.uguraltintas.rickandmorty.model.characters.CharacterModel
import com.uguraltintas.rickandmorty.model.relations.CharacterWithEpisodes

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character_table ORDER BY id")
    fun getAllCharactersFromDB() : List<CharacterModel>

    @Transaction
    @Query("SELECT * FROM character_table")
    fun getCharacterWithEpisodes() : List<CharacterWithEpisodes>

    @Query("SELECT * FROM character_table WHERE id = :id")
    suspend fun getCharacterById(id : Int) : CharacterModel

    @Insert()
    suspend fun insertCharacter(character: CharacterModel)



}