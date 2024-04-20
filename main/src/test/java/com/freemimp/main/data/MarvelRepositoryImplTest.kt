package com.freemimp.main.data

import com.freemimp.main.domain.MarvelApi
import com.freemimp.main.domain.MarvelRepository
import com.freemimp.main.domain.model.Comic
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class MarvelRepositoryImplTest {
    private val marvelApi: MarvelApi = mockk(relaxed = true)
    private val sut: MarvelRepository = MarvelRepositoryImpl(marvelApi)

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `given getComics is executed, when api call is successful, then return Result_success with comics`() {
        runTest {
            val comics = listOf<Comic>(mockk())
            coEvery { marvelApi.getComics() } returns Result.success(comics)

            val result = sut.getComics()
            val expected = Result.success(comics)

            assertEquals(expected, result)
        }
    }

    @Test
    fun `given getComics is executed, when api call is NOT successful, then return Result_success with comics`() {
        runTest {
            val throwable = Throwable()
            coEvery { marvelApi.getComics() } returns Result.failure(throwable)

            val result = sut.getComics()
            val expected = Result.failure<List<Comic>>(throwable)

            assertEquals(expected, result)
        }
    }
}
