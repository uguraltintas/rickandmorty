package com.uguraltintas.rickandmorty.ui.episodes.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.uguraltintas.rickandmorty.databinding.FragmentEpisodeDetailBinding
import com.uguraltintas.rickandmorty.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@AndroidEntryPoint
class EpisodeDetailFragment : BaseFragment<FragmentEpisodeDetailBinding>() {

    val episodeDetailViewModel : EpisodeDetailViewModel by viewModels()
    val args : EpisodeDetailFragmentArgs by navArgs()
    override fun getViewBinding(): FragmentEpisodeDetailBinding =
        FragmentEpisodeDetailBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = args.id

        episodeDetailViewModel.getEpisodeById(id)

        episodeDetailViewModel.episode.observe(viewLifecycleOwner,{
            Toast.makeText(context,it.episode!!,Toast.LENGTH_LONG).show()
        })

    }
}