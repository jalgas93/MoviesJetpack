package com.example.moviesjetpackcompose.http

import com.example.moviesjetpackcompose.modules.popular.models.MovieResponse
import com.example.moviesjetpackcompose.modules.popular.models.MoviesList
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiClient {
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3ZDZjM2MyNWZhNjZhODg2YjI3YzFjNDQzN2IwN2MxNiIsInN1YiI6IjVjODUxOWFlOTI1MTQxMjc2MzIxM2Y5OCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.KFrCRUIT73PL-cGXmWYkIG1Oo8F_pZoiUlc56Pc7S-o",
    )
    @GET("popular?language=en-US&page=1")
    suspend fun getMovies(): MoviesList
}