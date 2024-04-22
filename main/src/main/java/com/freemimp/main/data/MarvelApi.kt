package com.freemimp.main.data

import com.freemimp.main.domain.model.Comic

interface MarvelApi {
    suspend fun getComics(): Result<List<Comic>>
}
