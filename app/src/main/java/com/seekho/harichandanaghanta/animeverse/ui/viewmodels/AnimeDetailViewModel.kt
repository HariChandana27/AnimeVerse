package com.seekho.harichandanaghanta.animeverse.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.seekho.harichandanaghanta.animeverse.data.repository.AnimeRepository
import com.seekho.harichandanaghanta.animeverse.ui.data.AnimeDetailData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimeDetailViewModel(
    private val repository: AnimeRepository,
    private val animeId: Int
) : ViewModel() {

    private val _animeDetail = MutableStateFlow<AnimeDetailData?>(null)
    val animeDetail: StateFlow<AnimeDetailData?> = _animeDetail.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadAnimeDetails()
    }

    fun loadAnimeDetails() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                _animeDetail.value = repository.getAnimeDetails(animeId)
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "An unexpected error occurred while fetching details."
                _animeDetail.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }
}

class AnimeDetailViewModelFactory(
    private val repository: AnimeRepository,
    private val animeId: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimeDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AnimeDetailViewModel(repository, animeId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
