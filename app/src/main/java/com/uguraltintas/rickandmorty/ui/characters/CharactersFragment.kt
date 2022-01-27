package com.uguraltintas.rickandmorty.ui.characters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uguraltintas.rickandmorty.adapter.characters.CharactersAdapter
import com.uguraltintas.rickandmorty.databinding.FragmentCharactersBinding
import com.uguraltintas.rickandmorty.model.characters.CharacterModel
import com.uguraltintas.rickandmorty.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
@AndroidEntryPoint
class CharactersFragment : BaseFragment<FragmentCharactersBinding>() {
    val charactersViewModel: CharactersViewModel by viewModels()
    private var pageCount: Int = 1
    private var resultPages: Int = 0

    override fun getViewBinding(): FragmentCharactersBinding =
        FragmentCharactersBinding.inflate(layoutInflater)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvAdapter = CharactersAdapter(emptyList()) {character -> onItemClick(character)}
        binding.rvCharacters.adapter = rvAdapter
        val layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        binding.rvCharacters.layoutManager = layoutManager

        charactersViewModel.characterList.observe(viewLifecycleOwner) {
            if (it.first[1] == 0) {
                insertCharacterToDB(it.second!!)
            }
            rvAdapter.updateValue(it.second)
            resultPages = it.first[0]!!
            if (it.first[1] == 1) { // This means the data comes from DB
                pageCount = resultPages / 20 // Each page has 20 result
            }

        }

        charactersViewModel.getCharacterList(pageCount)

        binding.rvCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    if (pageCount >= resultPages) {
                        return
                    }
                    pageCount++
                    charactersViewModel.getCharacterList(pageCount)

                }
            }
        })



    }

    private fun onItemClick(character : CharacterModel){
        val action = CharactersFragmentDirections.actionCharactersToCharacterDetailFragment(character.id!!,character.name!!)
        findNavController().navigate(action)
    }

    private fun insertCharacterToDB(characterList : List<CharacterModel>){
        for (character in characterList){
            charactersViewModel.insertCharacter(character)
        }

    }


}