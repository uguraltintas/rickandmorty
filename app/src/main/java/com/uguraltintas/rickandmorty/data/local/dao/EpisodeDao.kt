package com.uguraltintas.rickandmorty.data.local.dao

import androidx.room.*
import com.uguraltintas.rickandmorty.model.episodes.EpisodeModel
import com.uguraltintas.rickandmorty.model.relations.EpisodeWithCharacters

@Dao
interface EpisodeDao {

    @Query("SELECT * FROM episode_table  ORDER BY id")
    fun getAllEpisodesFromDB() : List<EpisodeModel>

    @Query("SELECT * FROM episode_table WHERE id = :id")
    suspend fun getEpisodeById(id : Int) : EpisodeModel

    @Transaction
    @Query("SELECT * FROM episode_table")
    fun getEpisodeWithCharacters() : List<EpisodeWithCharacters>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEpisode(episode: EpisodeModel)
}