package com.uguraltintas.rickandmorty.adapter.characters

import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.uguraltintas.rickandmorty.R
import com.uguraltintas.rickandmorty.databinding.CharactersRowBinding
import com.uguraltintas.rickandmorty.model.characters.CharacterModel

class CharactersViewHolder(
    private val binding: CharactersRowBinding,
    private val onItemClick: (character: CharacterModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CharacterModel?) {
        binding.characterModel = item
        binding.flCharacters.animation =
            AnimationUtils.loadAnimation(itemView.context, R.anim.row_animation)
        binding.flCharacters.setOnClickListener {
            onItemClick(item!!)
        }
    }
}