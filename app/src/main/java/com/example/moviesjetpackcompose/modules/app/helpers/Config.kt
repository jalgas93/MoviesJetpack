package com.example.moviesjetpackcompose.modules.app.helpers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object Config {
    const val API_BASE_URL: String = "https://api.themoviedb.org/3/movie/"
    const val IMAGE_BASE_URL: String = "http://image.tmdb.org/t/p/w500"

    object Other {
        var isBottomBarVisible by mutableStateOf(true)
    }
}