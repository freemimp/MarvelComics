package com.freemimp.details.ui

import app.cash.turbine.test
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ComicsDetailsViewModelTest {

    private val sut = ComicsDetailsViewModel()

    @Test
    fun `given handle UiEvent is executed, when ui event is LoadImage, then return state as Success with image url`() {
        runTest {
            val imageUrl = "url"
            sut.handleUiEvents(UiEvent.LoadImage(imageUrl))

            sut.state.test {
                assertEquals(ComicsDetailsState.Success(imageUrl), awaitItem())
            }
        }
    }
}
