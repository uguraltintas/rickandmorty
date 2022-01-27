package com.uguraltintas.rickandmorty.ui.locations

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uguraltintas.rickandmorty.adapter.locations.LocationsAdapter
import com.uguraltintas.rickandmorty.databinding.FragmentLocationsBinding
import com.uguraltintas.rickandmorty.model.locations.LocationModel
import com.uguraltintas.rickandmorty.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@AndroidEntryPoint
class LocationsFragment : BaseFragment<FragmentLocationsBinding>() {
    val locationsViewModel: LocationsViewModel by viewModels()
    private var pageCount = 1
    private var resultPages = 0

    override fun getViewBinding(): FragmentLocationsBinding =
        FragmentLocationsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = LocationsAdapter(emptyList()) {location -> onItemClick(location)}
        binding.rvLocations.adapter = adapter
        val layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        binding.rvLocations.layoutManager = layoutManager


        locationsViewModel.locationList.observe(viewLifecycleOwner, {

            insertLocationToDB(it.second!!)
            adapter.updateValue(it.second!!)
            resultPages = it.first[0]!!
            if(it.first[1] == 1){ // This means the data comes from DB
                pageCount = resultPages / 20 // Each page has 20 result
            }
        })

        locationsViewModel.getLocationList(pageCount)

        binding.rvLocations.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    if (pageCount >= resultPages) {
                        return
                    }
                    pageCount++
                    locationsViewModel.getLocationList(pageCount)

                }
            }
        })
    }
    private fun onItemClick(location : LocationModel){
        val action = LocationsFragmentDirections.actionLocationsToLocationDetailFragment(location.id!!,location.name!!)
        findNavController().navigate(action)
    }

    private fun insertLocationToDB(locationList : List<LocationModel>){
        for(location in locationList){
            locationsViewModel.insertLocation(location)
        }
    }
}