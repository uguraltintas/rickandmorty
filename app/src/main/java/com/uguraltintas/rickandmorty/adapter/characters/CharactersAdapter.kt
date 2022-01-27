package com.uguraltintas.rickandmorty.adapter.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uguraltintas.rickandmorty.databinding.CharactersRowBinding
import com.uguraltintas.rickandmorty.model.characters.CharacterModel

class CharactersAdapter(
    private var characterList: List<CharacterModel>?,
    private val onItemClick : (character : CharacterModel) -> Unit
) : RecyclerView.Adapter<CharactersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CharactersRowBinding.inflate(inflater, parent, false)
        return CharactersViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(characterList?.get(position))
    }

    override fun getItemCount(): Int = characterList!!.size

    fun updateValue(newCharacterList: List<CharacterModel>?) {
        val mutableList: MutableList<CharacterModel> = ArrayList()
        mutableList.addAll(characterList!!)
        mutableList.addAll(newCharacterList!!)
        characterList = mutableList.toList()
        notifyDataSetChanged()
    }
}