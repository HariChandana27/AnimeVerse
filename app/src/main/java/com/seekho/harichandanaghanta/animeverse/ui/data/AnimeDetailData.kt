package com.seekho.harichandanaghanta.animeverse.ui.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnimeDetailData(
    val id: Int,
    val title: String?,
    val synopsis: String?,
    val episodes: Int?,
    val score: Double?,
    val genres: List<String>?,
    val trailerUrl: String?,
    val imagesUrl: String?
) : Parcelable
