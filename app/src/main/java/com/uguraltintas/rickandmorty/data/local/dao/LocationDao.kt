package com.uguraltintas.rickandmorty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uguraltintas.rickandmorty.model.locations.LocationModel

@Dao
interface LocationDao {

    @Query("SELECT * FROM location_table  ORDER BY id")
    fun getAllLocationsFromDB() : List<LocationModel>

    @Query("SELECT * FROM location_table WHERE id = :id")
    suspend fun getLocationById(id : Int) : LocationModel

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLocation(locationModel: LocationModel)
}