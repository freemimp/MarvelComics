package com.freemimp.main.ui

import app.cash.turbine.test
import com.freemimp.main.domain.MarvelRepository
import com.freemimp.main.domain.model.Comic
import com.freemimp.main.utils.TestCoroutineExtension
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MockKExtension::class, TestCoroutineExtension::class)
class ComicsListViewModelTest {

    private val repository: MarvelRepository = mockk(relaxed = true)

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `given viewmodel created, when repository call is successful, then state is Success with comics`() {
        runTest {
            val comics = listOf(
                Comic(
                    id = "id",
                    title = "title",
                    thumbnailUrl = "url",
                    fullImageUrl = "url"
                )
            )
            coEvery { repository.getComics() } returns Result.success(comics)

            val sut = createSut()

            sut.state.test {
                assertEquals(ComicsListState.Success(comics), awaitItem())
            }
        }
    }

    @Test
    fun `given viewmodel created, when repository call is NOT successful, then state is Error with error message`() {
        runTest {
            val message = "Test error"
            val exception = Exception(message)
            coEvery { repository.getComics() } returns Result.failure(exception)

            val sut = createSut()

            sut.state.test {
                assertEquals(ComicsListState.Error(exception.toString()), awaitItem())
            }
        }
    }

    @Test
    fun `given viewmodel created, when UiEvent is OnComicsClicked, then navigation event is NavigateToDetails with url`() {
        runTest {
            val comics = listOf(
                Comic(
                    id = "id",
                    title = "title",
                    thumbnailUrl = "url",
                    fullImageUrl = "url"
                )
            )
            coEvery { repository.getComics() } returns Result.success(comics)

            val sut = createSut()

            sut.navigationEvent.test {
                sut.handleUiEvent(UiEvent.OnComicsClicked(comics.first()))

                assertEquals(NavigationEvent.NavigateToDetails(comics.first()), awaitItem())
            }
        }
    }

    private fun createSut(): ComicsListViewModel = ComicsListViewModel(repository)
}
