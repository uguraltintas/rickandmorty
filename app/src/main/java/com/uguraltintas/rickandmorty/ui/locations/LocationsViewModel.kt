package com.uguraltintas.rickandmorty.ui.locations

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uguraltintas.rickandmorty.model.locations.LocationModel
import com.uguraltintas.rickandmorty.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class LocationsViewModel @Inject constructor(private val repository: RickAndMortyRepository) :
    ViewModel() {
    private val _locationList = MutableLiveData<Pair<List<Int?>, List<LocationModel>?>>()
    val locationList: LiveData<Pair<List<Int?>, List<LocationModel>?>>
        get() = _locationList

    fun getLocationList(page: Int) {
        GlobalScope.launch {
            val locationListFromDB: List<LocationModel> = repository.getLocationListFromDB()

            if (locationListFromDB.isNotEmpty()) {
                Log.e("LocationId", locationListFromDB[locationListFromDB.size - 1].id!!.toString())
                if (locationListFromDB[locationListFromDB.size - 1].id!! >= page * 20) {
                    val pair = Pair(listOf(locationListFromDB.size, 1), locationListFromDB)
                    _locationList.postValue(pair)
                } else {
                    getListFromService(page)
                }
            } else {
                getListFromService(page)
            }

        }
    }

    fun insertLocation(location: LocationModel) {
        GlobalScope.launch {
            repository.insertLocation(location)
        }
    }

    private fun getListFromService(page: Int) {
        GlobalScope.launch {
            val response = repository.getLocationListFromService(page)
            if (response.isSuccessful) {
                val pair =
                    Pair(listOf(response.body()?.info?.pages, 0), response.body()?.results)
                _locationList.postValue(pair)
            }
        }

    }
}