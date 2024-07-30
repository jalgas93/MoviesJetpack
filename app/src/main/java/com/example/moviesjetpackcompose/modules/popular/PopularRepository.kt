package com.example.moviesjetpackcompose.modules.popular

import android.util.Log
import com.example.moviesjetpackcompose.http.ApiClient
import com.example.moviesjetpackcompose.modules.app.Resources
import com.example.moviesjetpackcompose.modules.popular.models.MovieResponse
import java.lang.Exception
import javax.inject.Inject


interface PopularRepository {
    suspend fun getMovies(): Resources<List<MovieResponse>>
}

class PopularRepositoryImpl @Inject constructor(
    private val client: ApiClient
) : PopularRepository {
    override suspend fun getMovies(): Resources<List<MovieResponse>> {
        return try {
            Log.d("SUCCESS", client.getMovies().toString())
            Resources.Success(
                data = client.getMovies().results
            )
        } catch (e: Exception) {
            print(e.message.toString())
            e.printStackTrace()
            Resources.Error(e.message ?: "An unknown")
        }
    }
}