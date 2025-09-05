package com.seekho.harichandanaghanta.animeverse.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seekho.harichandanaghanta.animeverse.data.repository.AnimeRepository
import com.seekho.harichandanaghanta.animeverse.ui.data.AnimeDetailData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeListViewModel(private val repository: AnimeRepository = AnimeRepository()) : ViewModel() {

    private val _animeList = MutableStateFlow<List<AnimeDetailData>>(emptyList())
    val animeList: StateFlow<List<AnimeDetailData>> = _animeList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadAnimeList()
    }

    fun loadAnimeList() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                _animeList.value = repository.getAnimeList()
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "An unexpected error occurred"
                _animeList.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
