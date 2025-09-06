package com.seekho.harichandanaghanta.animeverse.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.seekho.harichandanaghanta.animeverse.R
import com.seekho.harichandanaghanta.animeverse.data.remote.GenreDetailData
import com.seekho.harichandanaghanta.animeverse.data.remote.ImageDetailData
import com.seekho.harichandanaghanta.animeverse.data.remote.JpgDetailData
import com.seekho.harichandanaghanta.animeverse.data.remote.TrailerDetailData
import com.seekho.harichandanaghanta.animeverse.data.repository.AnimeRepository
import com.seekho.harichandanaghanta.animeverse.ui.data.AnimeDetailData
import com.seekho.harichandanaghanta.animeverse.ui.theme.AnimeVerseTheme
import com.seekho.harichandanaghanta.animeverse.ui.viewmodels.AnimeDetailViewModel
import com.seekho.harichandanaghanta.animeverse.ui.viewmodels.AnimeDetailViewModelFactory

class AnimeDetailActivity : ComponentActivity() {

    companion object {
        const val EXTRA_ANIME_ID = "anime_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val animeId = intent.getIntExtra(EXTRA_ANIME_ID, -1)
        if (animeId == -1) {
            finish()
            return
        }

        val viewModel: AnimeDetailViewModel by viewModels {
            val repository = AnimeRepository()
            AnimeDetailViewModelFactory(repository, animeId)
        }

        setContent {
            AnimeVerseTheme {
                val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
                val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()
                val animeDetail by viewModel.animeDetail.collectAsStateWithLifecycle()

                AnimeDetailScreen(
                    isLoading = isLoading,
                    errorMessage = errorMessage,
                    animeDetail = animeDetail,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun AnimeDetailScreen(
    isLoading: Boolean,
    errorMessage: String?,
    animeDetail: AnimeDetailData?,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                errorMessage != null -> {
                    Text(
                        text = errorMessage,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                animeDetail != null -> {
                    AnimeDetailsView(
                        animeData = animeDetail,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun AnimeDetailsView(
    animeData: AnimeDetailData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = animeData.title ?: "No Title",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp)
        )

        GlideImage(
            model = animeData.imagesUrl,
            contentDescription = "${animeData.title ?: "Anime"} Poster",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop,
            loading = placeholder(R.drawable.ic_launcher_background),
            failure = placeholder(R.drawable.ic_launcher_background)
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Plot Summary",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = animeData.synopsis ?: "No synopsis available.",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(24.dp))

        HorizontalDivider()
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Details",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))

        DetailItem(label = "Genre(s)", value = animeData.genres?.joinToString(", ") ?: "N/A")
        DetailItem(label = "Episodes", value = animeData.episodes?.toString() ?: "N/A")
        DetailItem(label = "Rating", value = "${animeData.score ?: "N/A"}/10")
        animeData.trailerUrl?.let { trailerUrl ->
            if (trailerUrl.isNotBlank()) {
                 DetailItem(label = "Trailer", value = trailerUrl)
            }
        }
    }
}

@Composable
fun DetailItem(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(bottom = 12.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true, name = "AnimeDetailScreen - Loading")
@Composable
fun AnimeDetailScreenLoadingPreview() {
    AnimeVerseTheme {
        AnimeDetailScreen(
            isLoading = true,
            errorMessage = null,
            animeDetail = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true, name = "AnimeDetailScreen - Error Message")
@Composable
fun AnimeDetailScreenErrorPreview() {
    AnimeVerseTheme {
        AnimeDetailScreen(
            isLoading = false,
            errorMessage = "Failed to load anime details. Please try again.",
            animeDetail = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true, name = "AnimeDetailScreen - With Data (AnimeDetailsView)")
@Composable
fun AnimeDetailScreenWithDataPreview() {
    AnimeVerseTheme {
        AnimeDetailScreen(
            isLoading = false,
            errorMessage = null,
            animeDetail = AnimeDetailData(
                id = 1,
                title = "Full Preview Anime Title",
                synopsis = "Synopsis for full content preview. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                genresData = listOf(GenreDetailData("Fantasy"), GenreDetailData("Magic"), GenreDetailData("Adventure")),
                episodes = 24,
                score = 8.5,
                imagesData = ImageDetailData(JpgDetailData("http://example.com/image.jpg")), // Example URL
                trailerData = TrailerDetailData("http://example.com/trailer")
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}
