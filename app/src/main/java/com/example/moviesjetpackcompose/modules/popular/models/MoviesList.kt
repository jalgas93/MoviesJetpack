package com.example.moviesjetpackcompose.modules.popular.models

import kotlinx.serialization.Serializable

@Serializable
data class MoviesList(
    val results: List<MovieResponse>
)