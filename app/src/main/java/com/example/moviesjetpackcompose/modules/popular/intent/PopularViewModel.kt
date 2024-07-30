package com.example.moviesjetpackcompose.modules.popular.intent

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.moviesjetpackcompose.modules.app.BaseViewModel
import com.example.moviesjetpackcompose.modules.app.Resources
import com.example.moviesjetpackcompose.modules.popular.PopularRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(private val repository: PopularRepository) :
    BaseViewModel<PopularContract.Event, PopularContract.State, PopularContract.Effect>() {

    override fun setInitialState(): PopularContract.State = PopularContract.State(
        movies = emptyList(),
        isLoading = true,
        isError = false,
    )

    override fun handleEvents(event: PopularContract.Event) {
        when (event) {
            is PopularContract.Event.Retry -> getPosts()
            is PopularContract.Event.PopularSelection -> TODO()

//            is PopularContract.Event.HomeSelection -> setEffect {
//                PopularContract.Effect.Navigation.ToRepos(
//                    event.movie.id.toString()
//                )
//            }
        }
    }

    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch {

            when (val result = repository.getMovies()) {
                is Resources.Loading -> {
                    setState { copy(isLoading = true, isError = false) }
                }

                is Resources.Success -> {
                    setState { copy(movies = result.data!!, isLoading = false) }
                    setEffect { PopularContract.Effect.DataWasLoaded }
                }

                is Resources.Error -> {
                    setState { copy(isError = true, isLoading = false) }
                }
            }
        }
    }
}