package com.example.moviesjetpackcompose.modules.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.lifecycle.ScreenLifecycleOwner
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorContent
import cafe.adriel.voyager.navigator.OnBackPressed

class TabScreen : Screen, ScreenLifecycleOwner {
    @Composable
    override fun Content() {

        TabNavigator() {

            Scaffold(
                content = { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                    }
                },
                bottomBar = {
                    TabsBottomNavigationBar(
                        selectedItem = TabsBottomBarItems.HOME,
                        onItemSelected = { tab ->

                        },
                    )
                },
            )
        }
    }

}

@Composable
fun TabNavigator(
//    navigatorManager: NavigatorManager,
    onBackPressed: OnBackPressed = { true },
    content: NavigatorContent,
) {
    Navigator(
        screen = EmptyScreen,
//        disposeBehavior = NavigatorDisposeBehavior(
//            disposeNestedNavigators = false,
//            disposeSteps = false,
//        ),
        onBackPressed = onBackPressed,
    ) { navigator ->
//        DisposableEffect(Unit) {
////            navigatorManager.attachNavigator(navigator)
////            onDispose { navigatorManager.detachNavigator() }
//        }
        content.invoke(navigator)
    }
}

object EmptyScreen : Screen {
    @Composable
    override fun Content() = Unit
}
