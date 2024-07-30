package com.example.moviesjetpackcompose.modules.popular.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviesjetpackcompose.R
import com.example.moviesjetpackcompose.modules.app.helpers.Config
import com.example.moviesjetpackcompose.modules.app_common.view.MovieDetailsPage
import com.example.moviesjetpackcompose.modules.app_common.view.NextPage
import com.example.moviesjetpackcompose.modules.popular.intent.PopularContract
import com.example.moviesjetpackcompose.modules.popular.models.MovieResponse
import kotlinx.coroutines.flow.Flow

data class PopularPage(
    var state: State<PopularContract.State>,
    var effectFlow: Flow<PopularContract.Effect>?,
    var onEventSent: (event: PopularContract.Event) -> Unit,
//    var navigator: Navigator
) : Screen {
    @Composable
    override fun Content() {
        when {
            state.value.isLoading -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LoadingScreen()
            }

            state.value.isError -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                ErrorScreen(retryAction = { onEventSent(PopularContract.Event.Retry) })
            }

            else -> Box {
                LazyColumn(
                    modifier = Modifier
                        .padding(all = 16.dp)
                ) {
                    items(
                        items = state.value.movies
                    ) { movieItem ->
                        PopularItem(movieItem = movieItem)
                    }
                }
            }
        }
    }
}
//
//@Composable
//fun PopularPage(
//    state: PopularContract.State,
//    effectFlow: Flow<PopularContract.Effect>?,
//    onEventSent: (event: PopularContract.Event) -> Unit,
//    onNavigationRequested: (navigationEffect: PopularContract.Effect.Navigation) -> Unit
//) {
//
//    when {
//        state.isLoading -> Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.Center
//        ) {
//            LoadingScreen()
//        }
//
//        state.isError -> Box(
//            modifier = Modifier.fillMaxSize(),
//            contentAlignment = Alignment.Center
//        ) {
//            ErrorScreen(retryAction = { onEventSent(PopularContract.Event.Retry) })
//        }
//
//        else -> Box {
//            LazyColumn(
//                modifier = Modifier
//                    .padding(all = 16.dp)
//            ) {
//                items(
//                    items = state.movies
//                ) { movieItem ->
//                    PopularItem(movieItem = movieItem)
//                }
//            }
//        }
//    }
//
////    when (state) {
////        is state -> LoadingScreen(modifier = Modifier.fillMaxSize())
////        is PostError -> ErrorScreen(
////            retryAction = { viewModel.getPosts() },
////            modifier = Modifier.fillMaxSize(),
////        )
////
////        is PostsUiState.Success -> Box {
////            LazyColumn(modifier = Modifier.padding(all = 8.dp).clickable {
////                navController.navigate(route = Screen.Detail.passNameAndId(
////                    id = 12,
////                    name = "John"
////                ))
////            }) {
////                items(
////                    items = postUIState.posts
////                ) { postItem ->
////                    PostItem(postItem = postItem)
////                }
////            }
////        }
////    }
//}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = "Loading"
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = "Failed", modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text("Retry")
        }
    }
}

@Composable
fun PopularItem(movieItem: MovieResponse) {
    val navigate = LocalNavigator.currentOrThrow
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                navigate.push(MovieDetailsPage(movieItem))
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(Config.IMAGE_BASE_URL + movieItem.backdrop_path)
                .crossfade(true)
                .build(),
            contentDescription = movieItem.title,
            modifier = Modifier
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop,
        )
        Text(movieItem.title, style = TextStyle(color = Color.Black))
    }
}

@Composable
@Preview(showBackground = true)
fun DefaultPreview() {
    val listMovie: List<MovieResponse> =
        listOf(
            MovieResponse(id = 12, title = "asd", backdrop_path = ""),
            MovieResponse(id = 12, title = "asd", backdrop_path = ""),
            MovieResponse(id = 12, title = "asd", backdrop_path = ""),
            MovieResponse(id = 12, title = "asd", backdrop_path = ""),
        )
    LazyColumn(
        modifier = Modifier
            .padding(all = 16.dp)
    ) {
        items(
            items = listMovie
        ) { movieItem ->
            PopularItem(movieItem = movieItem)
        }
    }
}