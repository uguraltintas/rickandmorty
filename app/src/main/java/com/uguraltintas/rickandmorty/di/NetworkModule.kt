package com.uguraltintas.rickandmorty.di

import com.uguraltintas.rickandmorty.data.remote.RetrofitClient
import com.uguraltintas.rickandmorty.data.remote.RickAndMortyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit = RetrofitClient.getRetrofit()

    @Provides
    fun providesRickAndMortyService(retrofit: Retrofit) : RickAndMortyService = retrofit.create(RickAndMortyService::class.java)
}