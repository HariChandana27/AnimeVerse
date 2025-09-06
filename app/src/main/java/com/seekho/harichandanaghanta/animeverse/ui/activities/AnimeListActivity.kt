package com.seekho.harichandanaghanta.animeverse.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seekho.harichandanaghanta.animeverse.R
import com.seekho.harichandanaghanta.animeverse.data.remote.GenreDetailData
import com.seekho.harichandanaghanta.animeverse.ui.composables.AnimeListItem
import com.seekho.harichandanaghanta.animeverse.ui.data.AnimeDetailData
import com.seekho.harichandanaghanta.animeverse.ui.theme.AnimeVerseTheme
import com.seekho.harichandanaghanta.animeverse.ui.viewmodels.AnimeListViewModel

class AnimeListActivity : ComponentActivity() {

    private val animeListViewModel: AnimeListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimeVerseTheme {
                val animeList by animeListViewModel.animeList.collectAsState()
                val isLoading by animeListViewModel.isLoading.collectAsState()
                val errorMessage by animeListViewModel.errorMessage.collectAsState()

                AnimeScreen(
                    animeList = animeList,
                    isLoading = isLoading,
                    errorMessage = errorMessage
                )
            }
        }
    }
}

@Composable
fun AnimeScreen(
    modifier: Modifier = Modifier,
    animeList: List<AnimeDetailData>,
    isLoading: Boolean,
    errorMessage: String?
) {
    val context = LocalContext.current

    if (animeList.isEmpty() && isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    if (animeList.isEmpty() && errorMessage != null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
        }
        return
    }

    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
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
                    itemsIndexed(animeList) { index, anime ->
                        AnimeListItem(
                            title = anime.title ?: "No Title",
                            episodes = anime.episodes ?: 0,
                            rating = anime.score ?: 0.0,
                            imageUrl = anime.imagesUrl,
                            onItemClick = {
                                val intent = Intent(context, AnimeDetailActivity::class.java)
                                intent.putExtra("anime_id", anime.id)
                                context.startActivity(intent)
                            }
                        )
                        if (index < animeList.size - 1) {
                            Divider(modifier = Modifier.padding(horizontal = 16.dp))
                        }
                    }
                }
            }
            if (animeList.isNotEmpty() && isLoading) {
                 Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                 ) {
                     CircularProgressIndicator()
                 }
            }
        }
    }
}

@Preview(showBackground = true, name = "Default State")
@Composable
fun AnimeScreenPreview() {
    AnimeVerseTheme {
        val previewList = listOf(
            AnimeDetailData(
                id = 1, title = "Preview Anime 1", episodes = 12, score = 8.0, genresData = listOf(
                    GenreDetailData("Action")
                )
            ),
            AnimeDetailData(
                id = 2, title = "Preview Anime 2", episodes = 24, score = 8.5, genresData = listOf(
                    GenreDetailData("Comedy")
                )
            ),
        )
        AnimeScreen(animeList = previewList, isLoading = false, errorMessage = null)
    }
}

@Preview(showBackground = true, name = "Loading State")
@Composable
fun AnimeScreenLoadingPreview() {
    AnimeVerseTheme {
        AnimeScreen(animeList = emptyList(), isLoading = true, errorMessage = null)
    }
}

@Preview(showBackground = true, name = "Error State")
@Composable
fun AnimeScreenErrorPreview() {
    AnimeVerseTheme {
        AnimeScreen(animeList = emptyList(), isLoading = false, errorMessage = "Could not load anime. Please try again.")
    }
}
