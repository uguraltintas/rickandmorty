package com.uguraltintas.rickandmorty.ui.locations.detail

import com.uguraltintas.rickandmorty.databinding.FragmentLocationDetailBinding
import com.uguraltintas.rickandmorty.ui.base.BaseFragment


class LocationDetailFragment : BaseFragment<FragmentLocationDetailBinding>() {
    override fun getViewBinding(): FragmentLocationDetailBinding =
        FragmentLocationDetailBinding.inflate(layoutInflater)

}