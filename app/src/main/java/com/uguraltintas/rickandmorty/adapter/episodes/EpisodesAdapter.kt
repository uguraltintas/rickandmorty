package com.uguraltintas.rickandmorty.adapter.episodes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uguraltintas.rickandmorty.databinding.EpisodesRowBinding
import com.uguraltintas.rickandmorty.model.episodes.EpisodeModel

class EpisodesAdapter(
    private var episodeList: List<EpisodeModel>?,
    private val onItemClick : (item : EpisodeModel) -> Unit
) : RecyclerView.Adapter<EpisodesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EpisodesRowBinding.inflate(inflater, parent, false)
        return EpisodesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.bind(episodeList?.get(position), onItemClick)
    }

    override fun getItemCount(): Int = episodeList!!.size

    fun updateValue(newEpisodeList: List<EpisodeModel>?) {
        val mutableList: MutableList<EpisodeModel> = ArrayList()
        mutableList.addAll(episodeList!!)
        mutableList.addAll(newEpisodeList!!)
        episodeList = mutableList.toList()
        notifyDataSetChanged()
    }
}