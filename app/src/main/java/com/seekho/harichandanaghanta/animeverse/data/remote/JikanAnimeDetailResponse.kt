package com.seekho.harichandanaghanta.animeverse.data.remote

import com.google.gson.annotations.SerializedName
import com.seekho.harichandanaghanta.animeverse.ui.data.AnimeDetailData

data class JikanAnimeDetailResponse(
    @SerializedName("data")
    val data: AnimeDetailData
)
