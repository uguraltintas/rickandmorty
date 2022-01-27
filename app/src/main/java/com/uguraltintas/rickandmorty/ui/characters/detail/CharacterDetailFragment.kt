package com.uguraltintas.rickandmorty.ui.characters.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.uguraltintas.rickandmorty.databinding.FragmentCharacterDetailBinding
import com.uguraltintas.rickandmorty.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment<FragmentCharacterDetailBinding>() {

    val characterDetailViewModel : CharacterDetailViewModel by viewModels()
    val args : CharacterDetailFragmentArgs by navArgs()

    override fun getViewBinding(): FragmentCharacterDetailBinding =
        FragmentCharacterDetailBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = args.id
        characterDetailViewModel.getCharacterById(id)

        characterDetailViewModel.character.observe(viewLifecycleOwner , {
            binding.characterModel = it

            activity?.title = it.name
        })
    }


}