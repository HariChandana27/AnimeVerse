package com.seekho.harichandanaghanta.animeverse.ui.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.seekho.harichandanaghanta.animeverse.data.remote.GenreDetailData
import com.seekho.harichandanaghanta.animeverse.data.remote.ImageDetailData
import com.seekho.harichandanaghanta.animeverse.data.remote.TrailerDetailData
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnimeDetailData(
    @SerializedName("mal_id")
    val id: Int,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("synopsis")
    val synopsis: String? = null,

    @SerializedName("episodes")
    val episodes: Int? = null,

    @SerializedName("score")
    val score: Double? = null,

    @IgnoredOnParcel
    @SerializedName("genres")
    val genresData: List<GenreDetailData>? = null,

    @IgnoredOnParcel
    @SerializedName("trailer")
    val trailerData: TrailerDetailData? = null,

    @IgnoredOnParcel
    @SerializedName("images")
    val imagesData: ImageDetailData? = null

) : Parcelable {

    val genres: List<String>?
        get() = genresData?.mapNotNull { it.name }

    val trailerUrl: String?
        get() = trailerData?.url

    val imagesUrl: String?
        get() = imagesData?.jpg?.imageUrl
}
