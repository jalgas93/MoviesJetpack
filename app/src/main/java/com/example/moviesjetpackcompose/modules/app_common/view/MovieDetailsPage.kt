@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.moviesjetpackcompose.modules.app_common.view

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesjetpackcompose.modules.app.helpers.Config
import com.example.moviesjetpackcompose.modules.popular.models.MovieResponse

data class MovieDetailsPage(
    val movieItem: MovieResponse,
) : Screen {

    init {
        Config.Other.isBottomBarVisible = false
    }

    @Composable
    override fun Content() {
        val nav = LocalNavigator.currentOrThrow


        Box {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.background,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceVariant),
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Details") },
                            navigationIcon = {
                                Button(onClick = { nav?.pop() }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = "Back",
                                    )
                                }
                            }
                        )
                    },
                    content = { innerPadding ->
                        Box(
                            modifier = Modifier.padding(innerPadding),
                            contentAlignment = Alignment.Center,
                        ) {
                            Column(
                                modifier = Modifier
                                    .clickable {
                                        nav?.push(NextPage())
                                    }
                            ) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(Config.IMAGE_BASE_URL + movieItem.backdrop_path)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = movieItem.title,
                                    modifier = Modifier
                                        .aspectRatio(1f)
                                        .clip(RoundedCornerShape(12.dp)),
                                    contentScale = ContentScale.Crop,
                                )
                                Text(movieItem.title, style = TextStyle(color = Color.Black))
                            }
                        }
                    }
                )
            }
        }
    }
}
