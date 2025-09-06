package com.seekho.harichandanaghanta.animeverse.data.remote

import retrofit2.http.GET

interface JikanApiService {

    @GET("top/anime")
    suspend fun getTopAnime(): JikanApiResponse
}
