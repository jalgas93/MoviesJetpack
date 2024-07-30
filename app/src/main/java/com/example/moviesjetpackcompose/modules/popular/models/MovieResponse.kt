package com.example.moviesjetpackcompose.modules.popular.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val id: Int,
    val title: String,
    val backdrop_path: String
)
