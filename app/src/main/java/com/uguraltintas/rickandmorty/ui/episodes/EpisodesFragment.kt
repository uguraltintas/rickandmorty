package com.uguraltintas.rickandmorty.ui.episodes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uguraltintas.rickandmorty.adapter.episodes.EpisodesAdapter
import com.uguraltintas.rickandmorty.databinding.FragmentEpisodesBinding
import com.uguraltintas.rickandmorty.model.episodes.EpisodeModel
import com.uguraltintas.rickandmorty.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@AndroidEntryPoint
class EpisodesFragment : BaseFragment<FragmentEpisodesBinding>() {

    val episodesViewModel: EpisodesViewModel by viewModels()
    private var pageCount = 1
    private var resultPages = 0

    override fun getViewBinding(): FragmentEpisodesBinding =
        FragmentEpisodesBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        binding.rvEpisodes.layoutManager = layoutManager
        val adapter = EpisodesAdapter(emptyList()) { item -> onItemClick(item) }
        binding.rvEpisodes.adapter = adapter

        episodesViewModel.episodeList.observe(viewLifecycleOwner,{
            if(it.first[1]== 0){
                insertEpisodesToDB(it.second!!)
            }
            adapter.updateValue(it.second)
            resultPages = it.first[0]!!
            if(it.first[1] == 1){ // This means the data comes from DB
                pageCount = resultPages / 20 // Each page has 20 result
            }
        })

        episodesViewModel.getEpisodeList(pageCount)

        binding.rvEpisodes.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    if (pageCount >= resultPages) {
                        return
                    }
                    pageCount++
                    episodesViewModel.getEpisodeList(pageCount)

                }
            }
        })
    }

    private fun onItemClick(location : EpisodeModel){
        val action = EpisodesFragmentDirections.actionEpisodesFragmentToEpisodeDetailFragment(location.id!!,location.name!!)
        findNavController().navigate(action)
    }

    private fun insertEpisodesToDB(episodeList : List<EpisodeModel>){
        for(episode in episodeList){
            episodesViewModel.insertEpisode(episode)
        }
    }
}