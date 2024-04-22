package com.freemimp.main.ui

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.freemimp.main.R
import com.freemimp.main.domain.model.Comic

@Composable
fun ComicsListScreen(
    viewModel: ComicsListViewModel = viewModel(),
    navHost: NavHostController = rememberNavController()
) {
    val state = viewModel.state.collectAsState().value
    val navigationEvent = viewModel.navigationEvent.collectAsState(initial = null).value

    LaunchedEffect(key1 = navigationEvent) {
        when (navigationEvent) {
            is NavigationEvent.NavigateToDetails -> {
                val encodedImageUrl = Uri.encode(navigationEvent.comic.fullImageUrl)
                navHost.navigate("${ComicsListNavigationConstants.Details.name}/${encodedImageUrl}")
            }
            null -> {
                // do nothing
            }
        }
    }

    ComicsContent(state = state, uiEvent = viewModel::handleUiEvent)
}

@Composable
fun ComicsContent(
    state: ComicsListState,
    uiEvent: (UiEvent) -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            when (state) {
                is ComicsListState.Loading -> LoadingSection()
                is ComicsListState.Error -> ErrorSection(
                    modifier = Modifier,
                    message = state.message
                )

                is ComicsListState.Success -> ComicListSection(comics = state.comics, uiEvent = uiEvent)
            }
        }
    }
}

@Composable
fun ComicListSection(comics: List<Comic>, uiEvent: (UiEvent) -> Unit) {
    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
        items(comics) { comic ->
            ComicItem(comic) {
                uiEvent(UiEvent.OnComicsClicked(comic))
            }
        }
    }
}

@Composable
fun ComicItem(comic: Comic, uiEvent: (UiEvent) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .testTag("${LIST_ITEM_TAG}_${comic.id}")
            .clickable(onClick = {
                uiEvent(UiEvent.OnComicsClicked(comic))
            }),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val placeholder = R.drawable.vector_placeholder
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(comic.thumbnailUrl)
                    .crossfade(true)
                    .placeholder(placeholder)
                    .error(placeholder)
                    .build(),
                contentDescription = comic.title,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = comic.title,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun LoadingSection() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorSection(modifier: Modifier, message: String) {
    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = message)
    }
}

private const val LIST_ITEM_TAG = "listItemTag"
