package com.seekho.harichandanaghanta.animeverse.ui.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnimeDetailData(
    val id: Int,
    val title: String? = null,
    val synopsis: String? = null,
    val episodes: Int? = null,
    val score: Double? = null,
    val genres: List<String>? = null,
    val trailerUrl: String? = null,
    val imagesUrl: String? = null
) : Parcelable
