package com.uguraltintas.rickandmorty.adapter.locations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uguraltintas.rickandmorty.databinding.LocationsRowBinding
import com.uguraltintas.rickandmorty.model.locations.LocationModel

class LocationsAdapter(
    private var locationList : List<LocationModel>?,
    private val onItemClick : (location : LocationModel) -> Unit
): RecyclerView.Adapter<LocationsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LocationsRowBinding.inflate(inflater,parent,false)
        return LocationsViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        holder.bind(locationList?.get(position))
    }

    override fun getItemCount(): Int = locationList!!.size

    fun updateValue(newLocationList : List<LocationModel>? ){
        val mutableList : MutableList<LocationModel> = ArrayList()
        mutableList.addAll(locationList!!)
        mutableList.addAll(newLocationList!!)
        locationList = mutableList.toList()
        notifyDataSetChanged()
    }
}