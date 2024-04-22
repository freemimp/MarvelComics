package com.freemimp.main.data

import com.freemimp.main.data.mapper.ComicsMapper
import com.freemimp.main.domain.model.Comic
import javax.inject.Inject

class RetrofitMarvelApi @Inject constructor(
    private val service: MarvelApiService,
    private val mapper: ComicsMapper
) : MarvelApi {

    override suspend fun getComics(): Result<List<Comic>> {
        return try {
            val response = service.getComics()
            if (response.isSuccessful) {
                val body =
                    requireNotNull(response.body()) { "At this point body should not be null" }
                Result.success(mapper.map(body))
            } else {
                Result.failure(Throwable("api issues"))
            }
        } catch (t: Throwable) {
            Result.failure(t)
        }
    }
}
