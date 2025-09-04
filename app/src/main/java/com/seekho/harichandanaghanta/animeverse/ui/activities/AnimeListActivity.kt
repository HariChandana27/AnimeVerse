package com.seekho.harichandanaghanta.animeverse.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seekho.harichandanaghanta.animeverse.R
import com.seekho.harichandanaghanta.animeverse.ui.composables.AnimeListItem
import com.seekho.harichandanaghanta.animeverse.ui.data.AnimeDetailData
import com.seekho.harichandanaghanta.animeverse.ui.theme.AnimeVerseTheme

class AnimeListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimeVerseTheme {
                AnimeScreen()
            }
        }
    }
}

@Composable
fun AnimeScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val dummyAnimeList = listOf(
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

    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.welcome_header),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                itemsIndexed(dummyAnimeList) { index, anime ->
                    AnimeListItem(
                        title = anime.title ?: "No Title",
                        episodes = anime.episodes ?: 0,
                        rating = anime.score ?: 0.0,
                        onItemClick = {
                            val intent = Intent(context, AnimeDetailActivity::class.java)
                            intent.putExtra("anime_detail_data", anime)
                            context.startActivity(intent)
                        }
                    )
                    if (index < dummyAnimeList.size - 1) {
                        Divider(modifier = Modifier.padding(horizontal = 16.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeScreenPreview() {
    AnimeVerseTheme {
        AnimeScreen()
    }
}
