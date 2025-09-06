package com.seekho.harichandanaghanta.animeverse.data.remote

import com.google.gson.annotations.SerializedName
import com.seekho.harichandanaghanta.animeverse.ui.data.AnimeDetailData

data class JikanApiResponse(
    @SerializedName("data")
    val data: List<AnimeDetailData>
)
