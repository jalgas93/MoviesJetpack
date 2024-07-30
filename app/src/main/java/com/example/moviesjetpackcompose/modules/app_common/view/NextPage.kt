package com.example.moviesjetpackcompose.modules.app_common.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesjetpackcompose.modules.app.helpers.Config
import com.example.moviesjetpackcompose.modules.popular.models.MovieResponse

class NextPage : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    title = {
                        Text("movieItem.title")
                    })
            },
        ) {
            Column(
                modifier = Modifier.padding(it)
            ) {
//                AsyncImage(
//                    model = ImageRequest.Builder(LocalContext.current)
//                        .data(Config.IMAGE_BASE_URL + movieItem.backdrop_path)
//                        .crossfade(true)
//                        .build(),
//                    contentDescription = movieItem.title,
//                    modifier = Modifier
//                        .aspectRatio(1f)
//                        .clip(RoundedCornerShape(12.dp)),
//                    contentScale = ContentScale.Crop,
//                )
                Text("movieItem.title", style = TextStyle(color = Color.Black))
            }
        }
    }
}
