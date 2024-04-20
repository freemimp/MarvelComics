package com.freemimp.main.domain

import com.freemimp.main.domain.model.Comic

interface MarvelApi {
    suspend fun getComics(): Result<List<Comic>>
}
