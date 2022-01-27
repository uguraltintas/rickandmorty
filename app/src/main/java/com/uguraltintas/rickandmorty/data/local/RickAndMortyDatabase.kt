package com.uguraltintas.rickandmorty.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.uguraltintas.rickandmorty.data.local.dao.CharacterDao
import com.uguraltintas.rickandmorty.data.local.dao.EpisodeDao
import com.uguraltintas.rickandmorty.data.local.dao.LocationDao
import com.uguraltintas.rickandmorty.model.characters.CharacterModel
import com.uguraltintas.rickandmorty.model.episodes.EpisodeModel
import com.uguraltintas.rickandmorty.model.locations.LocationModel
import com.uguraltintas.rickandmorty.model.relations.CharacterAndEpisode
import com.uguraltintas.rickandmorty.util.Converters

@Database(
    entities = [CharacterModel::class, EpisodeModel::class, LocationModel::class,CharacterAndEpisode::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class RickAndMortyDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun locationDao(): LocationDao
    abstract fun episodeDao(): EpisodeDao

     companion object {

        private const val dbName = "rick_and_morty_database"

        @Volatile
        private var INSTANCE: RickAndMortyDatabase? = null

        fun getInstance(context: Context): RickAndMortyDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RickAndMortyDatabase::class.java,
                    "rick_and_morty_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}