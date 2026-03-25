package com.example.task17.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.fragment.app.Fragment
import com.example.task17.data.CharacterRepository
import com.example.task17.databinding.FragmentDetailBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = _binding!!
    private val repository = CharacterRepository()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.screenTitle.text = "Screen 3: Details"
        binding.screenSubtitle.text = "Loading character details..."
        loadCharacterDetails()
    }

    private fun loadCharacterDetails() {
        viewLifecycleOwner.lifecycleScope.launch {
            runCatching {
                withContext(Dispatchers.IO) { repository.getCharacterById(1) }
            }.onSuccess { character ->
                if (_binding == null) return@onSuccess
                binding.characterName.text = character.name
                binding.characterMeta.text = "${character.status} • ${character.species}"
                binding.screenSubtitle.text = "Gender: ${character.gender}"
                binding.detailText.text = "ID: ${character.id}\nFetched from Rick and Morty API."
            }.onFailure { error ->
                if (_binding == null) return@onFailure
                binding.screenSubtitle.text = "Failed to load: ${error.localizedMessage}"
                binding.detailText.text = "Please check internet connection."
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
