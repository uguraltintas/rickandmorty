package com.uguraltintas.rickandmorty.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class RetrofitClient {

    companion object {

        @Volatile
        private var INSTANCE: Retrofit? = null

        fun getRetrofit(): Retrofit {

            return INSTANCE ?: synchronized(this) {
                val instance = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://rickandmortyapi.com/api/")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}