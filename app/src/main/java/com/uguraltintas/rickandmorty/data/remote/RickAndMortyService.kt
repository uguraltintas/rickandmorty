package com.uguraltintas.rickandmorty.data.remote

import com.uguraltintas.rickandmorty.model.characters.Characters
import com.uguraltintas.rickandmorty.model.episodes.Episodes
import com.uguraltintas.rickandmorty.model.locations.Locations
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyService {

    @GET("character")
    suspend fun getCharacterList(@Query("page") page: Int): Response<Characters>

    @GET("location")
    suspend fun getLocationList(@Query("page") page: Int): Response<Locations>

    @GET("episode")
    suspend fun getEpisodeList(@Query("page") page: Int): Response<Episodes>
}