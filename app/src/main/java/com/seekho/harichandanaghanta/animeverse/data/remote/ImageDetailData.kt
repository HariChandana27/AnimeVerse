package com.seekho.harichandanaghanta.animeverse.data.remote

import com.google.gson.annotations.SerializedName

data class ImageDetailData(
    @SerializedName("jpg")
    val jpg: JpgDetailData?
)
