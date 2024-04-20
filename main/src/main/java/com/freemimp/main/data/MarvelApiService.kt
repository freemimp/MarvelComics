package com.freemimp.main.data

import com.freemimp.main.data.model.ComicsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiService {
    @GET("comics")
    suspend fun getComics(): Response<ComicsResponse>
}
