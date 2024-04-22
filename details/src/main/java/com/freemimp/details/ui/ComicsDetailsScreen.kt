package com.freemimp.details.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.freemimp.details.R

@Composable
fun ComicsDetailsScreen(
    viewModel: ComicsDetailsViewModel = viewModel(),
    imageUrl: String
) {
    ComicsDetailsContent(
        state = viewModel.state.collectAsState().value,
        imageUrl,
        viewModel::handleUiEvents
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ComicsDetailsContent(
    state: ComicsDetailsState,
    imageUrl: String,
    uiEvent: (UiEvent) -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {

            when (state) {
                is ComicsDetailsState.Loading -> LoadingSection()
                is ComicsDetailsState.Success -> ImageSection(fullImageUrl = state.imageUrl)
            }
            uiEvent(UiEvent.LoadImage(imageUrl))
        }
    }
}

@Composable
fun ImageSection(fullImageUrl: String) {
    val placeholder = R.drawable.vector_placeholder

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(fullImageUrl)
            .crossfade(true)
            .placeholder(placeholder)
            .error(placeholder)
            .build(),
        contentDescription = "Full image",
        modifier = Modifier
            .testTag(IMAGE_ITEM_TAG)
            .fillMaxSize(),
        fallback = rememberVectorPainter(image = Icons.Default.Warning),
        contentScale = ContentScale.Fit
    )
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

private const val IMAGE_ITEM_TAG = "imageItemTag"
