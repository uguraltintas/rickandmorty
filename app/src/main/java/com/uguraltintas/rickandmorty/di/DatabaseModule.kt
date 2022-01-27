package com.uguraltintas.rickandmorty.di

import android.content.Context
import com.uguraltintas.rickandmorty.data.local.RickAndMortyDatabase
import com.uguraltintas.rickandmorty.data.local.dao.CharacterDao
import com.uguraltintas.rickandmorty.data.local.dao.EpisodeDao
import com.uguraltintas.rickandmorty.data.local.dao.LocationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesRickAndMortyDatabase(@ApplicationContext applicationContext: Context): RickAndMortyDatabase {
        return RickAndMortyDatabase.getInstance(applicationContext)
    }

    @Provides
    fun providesCharacterDao(database: RickAndMortyDatabase): CharacterDao = database.characterDao()

    @Provides
    fun providesLocationDao(database: RickAndMortyDatabase): LocationDao = database.locationDao()

    @Provides
    fun providesEpisodeDao(database: RickAndMortyDatabase): EpisodeDao = database.episodeDao()


}