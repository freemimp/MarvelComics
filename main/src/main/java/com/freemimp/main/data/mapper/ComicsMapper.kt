package com.freemimp.main.data.mapper

import com.freemimp.main.data.model.ComicsResponse
import com.freemimp.main.domain.model.Comic
import javax.inject.Inject

class ComicsMapper @Inject constructor() {

    fun map(comicsResponse: ComicsResponse): List<Comic> {
        return comicsResponse.data.results.map { result ->
            val thumbnailUrl = "${result.thumbnail.path}.${result.thumbnail.extension}"
            val imageUrl = if (result.images.isNotEmpty()) {
                "${result.images.first().path}.${result.images.first().extension}"
            } else {
                thumbnailUrl
            }

            Comic(
                id = result.id,
                title = result.title,
                thumbnailUrl = thumbnailUrl,
                fullImageUrl = imageUrl
            )
        }
    }
}
