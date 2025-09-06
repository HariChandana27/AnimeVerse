package com.seekho.harichandanaghanta.animeverse.data.repository

import com.seekho.harichandanaghanta.animeverse.data.remote.RetrofitClient
import com.seekho.harichandanaghanta.animeverse.ui.data.AnimeDetailData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnimeRepository {

    private val jikanApiService = RetrofitClient.instance

    suspend fun getAnimeList(): List<AnimeDetailData> {
        return withContext(Dispatchers.IO) {
            val response = jikanApiService.getTopAnime()
            response.data
        }
    }

    suspend fun getAnimeDetails(animeId: Int): AnimeDetailData {
        return withContext(Dispatchers.IO) {
            val response = jikanApiService.getAnimeDetails(animeId)
            response.data
        }
    }
}
