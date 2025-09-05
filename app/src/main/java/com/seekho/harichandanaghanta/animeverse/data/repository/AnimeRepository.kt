package com.seekho.harichandanaghanta.animeverse.data.repository

import com.seekho.harichandanaghanta.animeverse.ui.data.AnimeDetailData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class AnimeRepository {

    suspend fun getAnimeList(): List<AnimeDetailData> {
        return withContext(Dispatchers.IO) {
            delay(1000)
            listOf(
                AnimeDetailData(
                    id = 1,
                    title = "Attack on Titan",
                    synopsis = "Humans fight Titans.",
                    episodes = 75,
                    score = 9.1,
                    genres = listOf("Action", "Dark Fantasy", "Post-apocalyptic"),
                    trailerUrl = null,
                    imagesUrl = null
                ),
                AnimeDetailData(
                    id = 2,
                    title = "Naruto Shippuden",
                    synopsis = "Ninja adventures.",
                    episodes = 500,
                    score = 8.7,
                    genres = listOf("Adventure", "Fantasy", "Martial Arts"),
                    trailerUrl = null,
                    imagesUrl = null
                ),
                AnimeDetailData(
                    id = 3,
                    title = "Death Note",
                    synopsis = "A notebook that kills.",
                    episodes = 37,
                    score = 8.9,
                    genres = listOf("Mystery", "Psychological Thriller", "Supernatural"),
                    trailerUrl = null,
                    imagesUrl = null
                ),
                 AnimeDetailData(
                    id = 4,
                    title = "One Punch Man",
                    synopsis = "A hero who wins with one punch.",
                    episodes = 24,
                    score = 8.8,
                    genres = listOf("Action", "Comedy", "Superhero"),
                    trailerUrl = null,
                    imagesUrl = null
                )
            )
        }
    }
}
