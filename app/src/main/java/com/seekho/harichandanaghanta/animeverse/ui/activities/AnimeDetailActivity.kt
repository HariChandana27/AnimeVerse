package com.seekho.harichandanaghanta.animeverse.ui.activities

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seekho.harichandanaghanta.animeverse.R
import com.seekho.harichandanaghanta.animeverse.ui.data.AnimeDetailData
import com.seekho.harichandanaghanta.animeverse.ui.theme.AnimeVerseTheme

class AnimeDetailActivity : ComponentActivity() {

    companion object {
        const val EXTRA_ANIME_DATA = "anime_detail_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val animeData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_ANIME_DATA, AnimeDetailData::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<AnimeDetailData>(EXTRA_ANIME_DATA)
        }

        setContent {
            AnimeVerseTheme {
                if (animeData != null) {
                    AnimeDetailScreen(
                        title = animeData.title ?: "No Title",
                        synopsis = animeData.synopsis ?: "No synopsis available.",
                        genres = animeData.genres?.joinToString(", ") ?: "N/A",
                        mainCast = "N/A",
                        episodes = animeData.episodes ?: 0,
                        rating = animeData.score ?: 0.0
                    )
                } else {
                    Text("Error: Anime data not found.")
                }
            }
        }
    }
}

@Composable
fun AnimeDetailScreen(
    title: String,
    synopsis: String,
    genres: String,
    mainCast: String,
    episodes: Int,
    rating: Double,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 20.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 8.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "$title Poster",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Plot Summary",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = synopsis,
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

            DetailItem(label = "Genre(s)", value = genres)
            DetailItem(label = "Episodes", value = episodes.toString())
            DetailItem(label = "Rating", value = "$rating/10")

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


@Preview(showBackground = true)
@Composable
fun AnimeDetailScreenPreview() {
    AnimeVerseTheme {
        AnimeDetailScreen(
            title = "Preview Anime Title",
            synopsis = "This is a captivating plot for the preview. It might be long and could wrap to multiple lines, demonstrating the scrolling behavior.",
            genres = "Action, Adventure, Sci-Fi",
            mainCast = "Actor X, Actress Y, Supporting Z",
            episodes = 12,
            rating = 9.2
        )
    }
}
