package com.example.task17.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.fragment.app.Fragment
import com.example.task17.data.CharacterRepository
import com.example.task17.databinding.FragmentListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding
        get() = _binding!!
    private val repository = CharacterRepository()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.screenTitle.text = "Screen 2: Character List"
        binding.screenSubtitle.text = "Loading characters..."
        loadCharactersPage()
    }

    private fun loadCharactersPage() {
        viewLifecycleOwner.lifecycleScope.launch {
            runCatching {
                withContext(Dispatchers.IO) { repository.getCharactersPage(1) }
            }.onSuccess { characters ->
                if (_binding == null) return@onSuccess
                val listText = characters.take(10).joinToString(separator = "\n") {
                    "${it.id}. ${it.name} - ${it.species}"
                }
                binding.screenSubtitle.text = listText
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
