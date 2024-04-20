package com.freemimp.main.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freemimp.main.domain.MarvelRepository
import com.freemimp.main.domain.model.Comic
import com.freemimp.main.utils.safeGetOrThrow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicsListViewModel @Inject constructor(private val repository: MarvelRepository) :
    ViewModel() {
    private val _state = MutableStateFlow<ComicsListState>(ComicsListState.Loading)
    val state: StateFlow<ComicsListState> = _state

    private val _navigationEvent = MutableSharedFlow<NavigationEvent>()
    val navigationEvent: SharedFlow<NavigationEvent> = _navigationEvent

    private lateinit var cachedComics: List<Comic>

    init {
        fetchComics()
    }


    fun handleUiEvent(event: UiEvent) {
        viewModelScope.launch {
            when (event) {
                is UiEvent.OnComicsClicked -> {
                    _navigationEvent.emit(NavigationEvent.NavigateToDetails(event.comic))
                }
            }
        }
    }

    private fun fetchComics() {
        viewModelScope.launch {
            try {
                val comics = if (::cachedComics.isInitialized.not()) {
                    repository.getComics().safeGetOrThrow()
                } else {
                    cachedComics
                }
                cachedComics = comics
                _state.value = ComicsListState.Success(comics)
            } catch (e: Exception) {
                _state.value = ComicsListState.Error(e.toString())
            }
        }
    }
}

sealed class NavigationEvent {
    data class NavigateToDetails(val comic: Comic) : NavigationEvent()
}

sealed class ComicsListState {
    data object Loading : ComicsListState()
    data class Success(val comics: List<Comic>) : ComicsListState()
    data class Error(val message: String) : ComicsListState()
}

sealed interface UiEvent {
    data class OnComicsClicked(val comic: Comic) : UiEvent
}
