package com.freemimp.main.data

import com.freemimp.main.domain.MarvelRepository
import com.freemimp.main.domain.model.Comic
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(private val marvelApi: MarvelApi): MarvelRepository {
    override suspend fun getComics(): Result<List<Comic>> {
       return marvelApi.getComics()
    }
}
