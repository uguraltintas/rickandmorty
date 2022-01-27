package com.uguraltintas.rickandmorty.adapter.episodes

import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.uguraltintas.rickandmorty.R
import com.uguraltintas.rickandmorty.databinding.EpisodesRowBinding
import com.uguraltintas.rickandmorty.model.episodes.EpisodeModel

class EpisodesViewHolder(private val binding : EpisodesRowBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: EpisodeModel?, onItemClick: (item: EpisodeModel) -> Unit){
        binding.episodeModel = item
        binding.cvEpisode.animation = AnimationUtils.loadAnimation(itemView.context, R.anim.row_animation)
        binding.cvEpisode.setOnClickListener {
            onItemClick(item!!)
        }


    }
}