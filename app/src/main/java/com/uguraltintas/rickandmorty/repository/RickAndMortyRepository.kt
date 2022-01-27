package com.uguraltintas.rickandmorty.repository

import com.uguraltintas.rickandmorty.data.local.dao.CharacterDao
import com.uguraltintas.rickandmorty.data.local.dao.EpisodeDao
import com.uguraltintas.rickandmorty.data.local.dao.LocationDao
import com.uguraltintas.rickandmorty.data.remote.RickAndMortyService
import com.uguraltintas.rickandmorty.model.characters.CharacterModel
import com.uguraltintas.rickandmorty.model.episodes.EpisodeModel
import com.uguraltintas.rickandmorty.model.locations.LocationModel
import javax.inject.Inject

class RickAndMortyRepository @Inject constructor(
    private val service: RickAndMortyService,
    private val characterDao: CharacterDao,
    private val episodeDao: EpisodeDao,
    private val locationDao: LocationDao
) {

    fun getCharacterListFromDB() = characterDao.getAllCharactersFromDB()

    fun getLocationListFromDB() = locationDao.getAllLocationsFromDB()

    fun getEpisodeListFromDB() = episodeDao.getAllEpisodesFromDB()

    suspend fun getCharacterById(id : Int) = characterDao.getCharacterById(id)

    suspend fun getLocationById(id : Int) = locationDao.getLocationById(id)

    suspend fun getEpisodeById(id : Int) = episodeDao.getEpisodeById(id)

    fun getCharacterWithEpisodes() = characterDao.getCharacterWithEpisodes()

    suspend fun insertCharacter(character: CharacterModel) = characterDao.insertCharacter(character)

    suspend fun insertLocation(location: LocationModel) = locationDao.insertLocation(location)

    suspend fun insertEpisode(episode: EpisodeModel) = episodeDao.insertEpisode(episode)

    suspend fun getCharacterListFromService(page: Int) = service.getCharacterList(page)

    suspend fun getLocationListFromService(page: Int) = service.getLocationList(page)

    suspend fun getEpisodeListFromService(page: Int) = service.getEpisodeList(page)
}