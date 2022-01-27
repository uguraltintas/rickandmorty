package com.uguraltintas.rickandmorty.adapter.locations

import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.uguraltintas.rickandmorty.R
import com.uguraltintas.rickandmorty.databinding.LocationsRowBinding
import com.uguraltintas.rickandmorty.model.locations.LocationModel

class LocationsViewHolder(
    private val binding: LocationsRowBinding,
    private val onItemClick: (location: LocationModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: LocationModel?) {
        binding.locationModel = item
        binding.cvLocation.animation =
            AnimationUtils.loadAnimation(itemView.context, R.anim.row_animation)
        binding.cvLocation.setOnClickListener {
            onItemClick(item!!)
        }
    }
}