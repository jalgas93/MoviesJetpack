package com.example.moviesjetpackcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorContent
import cafe.adriel.voyager.navigator.OnBackPressed
import com.example.moviesjetpackcompose.modules.dashboard.DashboardPage
import com.example.moviesjetpackcompose.modules.dashboard.TabScreen
import com.example.moviesjetpackcompose.modules.popular.intent.PopularViewModel
import com.example.moviesjetpackcompose.ui.theme.MoviesJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesJetpackComposeTheme {
                DashboardPage()
            }
        }
    }
}

@Composable
fun AppNavigator(
    initialScreen: Screen = EmptyScreen,
    onBackPressed: OnBackPressed = { true },
    content: NavigatorContent = { CurrentScreen() },
) {
    Navigator(
        screen = initialScreen,
        onBackPressed = onBackPressed,
//        disposeBehavior = NavigatorDisposeBehavior(disposeNestedNavigators = false),
    ) { navigator ->
//        DisposableEffect(Unit) {
//            navigatorManager.attachNavigator(navigator)
//            onDispose { navigatorManager.detachNavigator() }
//        }
        content.invoke(navigator)
    }
}

object EmptyScreen : Screen {
    @Composable
    override fun Content() = Unit
}
