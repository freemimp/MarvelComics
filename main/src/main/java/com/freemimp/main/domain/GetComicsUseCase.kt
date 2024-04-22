package com.freemimp.main.domain

import com.freemimp.main.domain.model.Comic
import javax.inject.Inject

class GetComicsUseCase @Inject constructor(private val repository: MarvelRepository) {
    suspend fun execute(): Result<List<Comic>> {
        return repository.getComics()
    }
}
