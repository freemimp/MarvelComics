package com.freemimp.details.ui

import androidx.lifecycle.ViewModel
import com.freemimp.details.ui.UiEvent.LoadImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ComicsDetailsViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow<ComicsDetailsState>(ComicsDetailsState.Loading)
    val state: StateFlow<ComicsDetailsState> = _state

    fun handleUiEvents(uiEvent: UiEvent) {
        when (uiEvent) {
            is LoadImage -> _state.value = ComicsDetailsState.Success(uiEvent.imageUrl)
        }
    }
}

sealed class ComicsDetailsState {
    data class Success(val imageUrl: String) : ComicsDetailsState()
    data object Loading : ComicsDetailsState()
}

sealed interface UiEvent {
    data class LoadImage(val imageUrl: String) : UiEvent
}
