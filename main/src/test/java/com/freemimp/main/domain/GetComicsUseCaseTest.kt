package com.freemimp.main.domain

import com.freemimp.main.domain.model.Comic
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class GetComicsUseCaseTest {
    private val repository: MarvelRepository = mockk(relaxed = true)

    private val sut: GetComicsUseCase = GetComicsUseCase(repository)

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `given execute is invoked, when repository call is successful, then return Result_success with comics`() {
        runTest {
            val comics = listOf<Comic>(mockk())
            coEvery { repository.getComics() } returns Result.success(comics)

            val result = sut.execute()
            val expected = Result.success(comics)

            Assertions.assertEquals(expected, result)
        }
    }

    @Test
    fun `given execute is invoked, when repository call is NOT successful, then return Result_failure with error`() {
        runTest {
            val throwable = Throwable()
            coEvery { repository.getComics() } returns Result.failure(throwable)

            val result = sut.execute()
            val expected = Result.failure<List<Comic>>(throwable)

            Assertions.assertEquals(expected, result)
        }
    }
}
