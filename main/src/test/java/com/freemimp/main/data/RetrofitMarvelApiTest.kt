package com.freemimp.main.data

import com.freemimp.main.data.mapper.ComicsMapper
import com.freemimp.main.data.model.ComicsResponse
import com.freemimp.main.domain.MarvelApi
import com.freemimp.main.domain.model.Comic
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import retrofit2.Response
import java.net.HttpURLConnection.HTTP_NOT_FOUND
import java.net.HttpURLConnection.HTTP_OK

@ExtendWith(MockKExtension::class)
class RetrofitMarvelApiTest {
    private val mapper: ComicsMapper = mockk(relaxed = true)
    private val service: MarvelApiService = mockk(relaxed = true)

    private val sut: MarvelApi = RetrofitMarvelApi(service, mapper)

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `given getComics is executed, when service an mapper calls are successful, then return Result_success with Comics`() {
        runTest {
            val response = mockk<ComicsResponse>()
            val comics = listOf<Comic>(mockk())
            coEvery { service.getComics() } returns Response.success(HTTP_OK, response)
            every { mapper.map(response) } returns comics

            val result = sut.getComics()
            val expected = Result.success(comics)

            assertEquals(expected, result)
            coVerifySequence {
                service.getComics()
                mapper.map(response)
            }
        }
    }

    @Test
    fun `given getComics is executed, when service call is not successful, but mapper is, then return Result_failure with error`() {
        runTest {
            val response = mockk<ResponseBody>(relaxed = true)
            coEvery { service.getComics() } returns Response.error(HTTP_NOT_FOUND, response)

            val result = sut.getComics()
            val expected = Result.failure<List<Comic>>(Throwable("api issues"))

            assertEquals(expected.exceptionOrNull()?.message, result.exceptionOrNull()?.message)
            coVerifySequence {
                service.getComics()
            }
        }
    }

    @Test
    fun `given getComics is executed, when service call is successful, but mapper is not, then return Result_failure with error`() {
        runTest {
            val throwable = Throwable("This is test")
            val response = mockk<ComicsResponse>()
            coEvery { service.getComics() } returns Response.success(HTTP_OK, response)
            every { mapper.map(response) } throws throwable

            val result = sut.getComics()
            val expected = Result.failure<List<Comic>>(throwable)

            assertEquals(expected, result)
            coVerifySequence {
                service.getComics()
                mapper.map(response)
            }
        }
    }
}
