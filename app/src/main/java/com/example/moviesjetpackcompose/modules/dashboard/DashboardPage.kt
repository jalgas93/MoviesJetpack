package com.example.moviesjetpackcompose.modules.dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.moviesjetpackcompose.modules.app.helpers.Config
import com.example.moviesjetpackcompose.modules.app_common.view.MovieDetailsPage
import com.example.moviesjetpackcompose.modules.dashboard.tabs.FavouriteTab
import com.example.moviesjetpackcompose.modules.dashboard.tabs.HomeTab
import com.example.moviesjetpackcompose.modules.dashboard.tabs.TopRatingTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardPage() {
    MaterialTheme {
        TabNavigator(tab = HomeTab) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        title = {
                            Text("Movies")
                        })
                },
                bottomBar = {
                    AnimVisible(isVisible = Config.Other.isBottomBarVisible) {
                        TabsBottomNavigationBar(selectedItem = TabsBottomBarItems.HOME) { tab ->

                        }
                    }
                }
            ) {
                Box(
                    modifier = Modifier.padding(it)
                ) {
                    CurrentTab()
                }
            }
        }
    }
}

@Composable
fun AnimVisible(
    isVisible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = isVisible,
        exit = fadeOut(),
        enter = fadeIn()
    ) {
        content()
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        label = { Text(tab.options.title, style = TextStyle(color = Color.White)) },
        icon = {
            if (tab.options.icon != null) {
                Icon(painter = tab.options.icon!!, contentDescription = "")
            }
        }
    )
}

@Composable
fun TabsBottomNavigationBar(
    modifier: Modifier = Modifier,
    selectedItem: TabsBottomBarItems,
    onItemSelected: (TabsBottomBarItems) -> Unit,
) {
    BottomNavigationBar(
        modifier = modifier.height(80.dp),
        selectedItem = selectedItem,
        items = TabsBottomBarItems.values(),
        showLabel = true,
        onItemSelected = onItemSelected,
    )
}

enum class TabsBottomBarItems : BottomBarItem {
    HOME {
        override val label: String @Composable get() = "qwe"
    },
    ANALYTICS {
        override val label: String @Composable get() = "asd"
    },
    SETTINGS {
        override val label: String @Composable get() = "Profile"
    },
}

@Composable
fun <Item : BottomBarItem> BottomNavigationBar(
    modifier: Modifier,
    selectedItem: Item,
    items: Array<Item>,
    showLabel: Boolean,
    onItemSelected: (Item) -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = selectedItem == item,
                onClick = { onItemSelected.invoke(item) },
                icon = {
                    BottomBarIcon(
                        selected = selectedItem == item,
                        description = item.label,
                    )
                },
                label = if (showLabel) { {
                    BottomBarLabel(
                        selected = selectedItem == item,
                        title = item.label,
                    )
                } } else {
                    null
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                ),
            )
        }
    }
}

@Composable
fun BottomBarIcon(
    selected: Boolean,
//    enabledIcon: Painter,
//    disabledIcon: Painter,
    description: String,
) {
//    Icon(
//        painter = if (selected) enabledIcon else disabledIcon,
//        contentDescription = description,
//        tint = when (selected) {
//            true -> MaterialTheme.colorScheme.onSecondaryContainer
//            false -> MaterialTheme.colorScheme.onSurfaceVariant
//        },
//    )
}

@Composable
fun BottomBarLabel(
    selected: Boolean,
    title: String,
) {
    Text(
        text = title,
        color = when (selected) {
            true -> MaterialTheme.colorScheme.onSurface
            false -> MaterialTheme.colorScheme.onSurfaceVariant
        },
        style = MaterialTheme.typography.labelMedium,
    )
}

interface BottomBarItem {
    val label: String @Composable get
//    val enabledIcon: Int @Composable get
//    val disabledIcon: Int @Composable get
}
