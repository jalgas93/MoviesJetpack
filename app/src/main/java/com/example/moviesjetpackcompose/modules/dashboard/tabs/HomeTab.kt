package com.example.moviesjetpackcompose.modules.dashboard.tabs

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.moviesjetpackcompose.modules.app.helpers.Config
import com.example.moviesjetpackcompose.modules.app_common.view.MovieDetailsPage
import com.example.moviesjetpackcompose.modules.app_common.view.NextPage
import com.example.moviesjetpackcompose.modules.popular.intent.PopularContract
import com.example.moviesjetpackcompose.modules.popular.intent.PopularViewModel
import com.example.moviesjetpackcompose.modules.popular.models.MovieResponse
import com.example.moviesjetpackcompose.modules.popular.view.PopularPage

object HomeTab : Tab {
    @Composable
    override fun Content() {
        val viewModel: PopularViewModel = hiltViewModel()
        Navigator(
            PopularPage(
                state = viewModel.viewState,
                effectFlow = viewModel.effect,
                onEventSent = { event -> viewModel.setEvent(event) },
//                navigator = checkNotNull(LocalNavigator.current)
//            onNavigationRequested = { navigationEffect ->
//                if (navigationEffect is PopularContract.Effect.Navigation.ToRepos) {
////                            navController.navigateToRepos(navigationEffect.userId)
//                }
//            }
            ),
            onBackPressed = {
                Config.Other.isBottomBarVisible = false
                true
            }
        )
    }

    override val options: TabOptions
        @Composable
        get() = remember {
            TabOptions(
                index = 0u,
                title = "Home",
                icon = null
            )
        }

    private fun readResolve(): Any = HomeTab
}