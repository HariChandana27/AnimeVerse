package com.seekho.harichandanaghanta.animeverse.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface JikanApiService {

    @GET("top/anime")
    suspend fun getTopAnime(): JikanApiResponse

    @GET("anime/{id}")
    suspend fun getAnimeDetails(@Path("id") animeId: Int): JikanAnimeDetailResponse
}
