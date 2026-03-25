package com.example.task17.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.fragment.app.Fragment
import com.example.task17.data.CharacterRepository
import com.example.task17.databinding.FragmentSearchBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = _binding!!
    private val repository = CharacterRepository()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.screenTitle.text = "Screen 1: Filter/Favorites"
        binding.screenSubtitle.text = "Loading search results..."
        loadSearchResults()
    }

    private fun loadSearchResults() {
        viewLifecycleOwner.lifecycleScope.launch {
            runCatching {
                withContext(Dispatchers.IO) { repository.searchCharacters("rick") }
            }.onSuccess { characters ->
                if (_binding == null) return@onSuccess
                val names = characters.take(5).joinToString(separator = "\n") {
                    "${it.name} (${it.status})"
                }
                binding.screenSubtitle.text = "Query: rick\n$names"
            }.onFailure { error ->
                if (_binding == null) return@onFailure
                binding.screenSubtitle.text = "Failed to load: ${error.localizedMessage}"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
