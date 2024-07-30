package com.example.moviesjetpackcompose.modules.popular.intent

import com.example.moviesjetpackcompose.modules.app.ViewEvent
import com.example.moviesjetpackcompose.modules.app.ViewSideEffect
import com.example.moviesjetpackcompose.modules.app.ViewState
import com.example.moviesjetpackcompose.modules.popular.models.MovieResponse

class PopularContract {
    sealed class Event : ViewEvent {
        object Retry : Event()
        data class PopularSelection(val movie: MovieResponse) : Event()
    }

    data class State(
        val movies: List<MovieResponse>,
        val isLoading: Boolean,
        val isError: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        object DataWasLoaded : Effect()

        sealed class Navigation : Effect() {
            data class ToRepos(val userId: String) : Navigation()
        }
    }

}