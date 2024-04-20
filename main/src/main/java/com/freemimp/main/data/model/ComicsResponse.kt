package com.freemimp.main.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComicsResponse(
    @Json(name = "attributionHTML")
    val attributionHTML: String,
    @Json(name = "attributionText")
    val attributionText: String,
    @Json(name = "code")
    val code: String,
    @Json(name = "copyright")
    val copyright: String,
    @Json(name = "data")
    val `data`: Data,
    @Json(name = "etag")
    val etag: String,
    @Json(name = "status")
    val status: String
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "count")
        val count: String,
        @Json(name = "limit")
        val limit: String,
        @Json(name = "offset")
        val offset: String,
        @Json(name = "results")
        val results: List<Result>,
        @Json(name = "total")
        val total: String
    ) {
        @JsonClass(generateAdapter = true)
        data class Result(
            @Json(name = "characters")
            val characters: Characters,
            @Json(name = "collectedIssues")
            val collectedIssues: List<CollectedIssue>,
            @Json(name = "collections")
            val collections: List<Collection>,
            @Json(name = "creators")
            val creators: Creators,
            @Json(name = "dates")
            val dates: List<Date>,
            @Json(name = "description")
            val description: String?,
            @Json(name = "diamondCode")
            val diamondCode: String,
            @Json(name = "digitalId")
            val digitalId: String,
            @Json(name = "ean")
            val ean: String,
            @Json(name = "events")
            val events: Events,
            @Json(name = "format")
            val format: String,
            @Json(name = "id")
            val id: String,
            @Json(name = "images")
            val images: List<Image>,
            @Json(name = "isbn")
            val isbn: String,
            @Json(name = "issn")
            val issn: String,
            @Json(name = "issueNumber")
            val issueNumber: String,
            @Json(name = "modified")
            val modified: String,
            @Json(name = "pageCount")
            val pageCount: String,
            @Json(name = "prices")
            val prices: List<Price>,
            @Json(name = "resourceURI")
            val resourceURI: String,
            @Json(name = "series")
            val series: Series,
            @Json(name = "stories")
            val stories: Stories,
            @Json(name = "textObjects")
            val textObjects: List<TextObject>,
            @Json(name = "thumbnail")
            val thumbnail: Thumbnail,
            @Json(name = "title")
            val title: String,
            @Json(name = "upc")
            val upc: String,
            @Json(name = "urls")
            val urls: List<Url>,
            @Json(name = "variantDescription")
            val variantDescription: String,
            @Json(name = "variants")
            val variants: List<Variant>
        ) {
            @JsonClass(generateAdapter = true)
            data class Characters(
                @Json(name = "available")
                val available: String,
                @Json(name = "collectionURI")
                val collectionURI: String,
                @Json(name = "items")
                val items: List<Item>,
                @Json(name = "returned")
                val returned: String
            ) {
                @JsonClass(generateAdapter = true)
                data class Item(
                    @Json(name = "name")
                    val name: String,
                    @Json(name = "resourceURI")
                    val resourceURI: String,
                    @Json(name = "role")
                    val role: String?
                )
            }

            @JsonClass(generateAdapter = true)
            data class CollectedIssue(
                @Json(name = "name")
                val name: String,
                @Json(name = "resourceURI")
                val resourceURI: String
            )

            @JsonClass(generateAdapter = true)
            data class Collection(
                @Json(name = "name")
                val name: String,
                @Json(name = "resourceURI")
                val resourceURI: String
            )

            @JsonClass(generateAdapter = true)
            data class Creators(
                @Json(name = "available")
                val available: String,
                @Json(name = "collectionURI")
                val collectionURI: String,
                @Json(name = "items")
                val items: List<Item>,
                @Json(name = "returned")
                val returned: String
            ) {
                @JsonClass(generateAdapter = true)
                data class Item(
                    @Json(name = "name")
                    val name: String,
                    @Json(name = "resourceURI")
                    val resourceURI: String,
                    @Json(name = "role")
                    val role: String
                )
            }

            @JsonClass(generateAdapter = true)
            data class Date(
                @Json(name = "date")
                val date: String,
                @Json(name = "type")
                val type: String
            )

            @JsonClass(generateAdapter = true)
            data class Events(
                @Json(name = "available")
                val available: String,
                @Json(name = "collectionURI")
                val collectionURI: String,
                @Json(name = "items")
                val items: List<Item>,
                @Json(name = "returned")
                val returned: String
            ) {
                @JsonClass(generateAdapter = true)
                data class Item(
                    @Json(name = "name")
                    val name: String,
                    @Json(name = "resourceURI")
                    val resourceURI: String
                )
            }

            @JsonClass(generateAdapter = true)
            data class Image(
                @Json(name = "extension")
                val extension: String,
                @Json(name = "path")
                val path: String
            )

            @JsonClass(generateAdapter = true)
            data class Price(
                @Json(name = "price")
                val price: String,
                @Json(name = "type")
                val type: String
            )

            @JsonClass(generateAdapter = true)
            data class Series(
                @Json(name = "name")
                val name: String,
                @Json(name = "resourceURI")
                val resourceURI: String
            )

            @JsonClass(generateAdapter = true)
            data class Stories(
                @Json(name = "available")
                val available: String,
                @Json(name = "collectionURI")
                val collectionURI: String,
                @Json(name = "items")
                val items: List<Item>,
                @Json(name = "returned")
                val returned: String
            ) {
                @JsonClass(generateAdapter = true)
                data class Item(
                    @Json(name = "name")
                    val name: String,
                    @Json(name = "resourceURI")
                    val resourceURI: String,
                    @Json(name = "type")
                    val type: String
                )
            }

            @JsonClass(generateAdapter = true)
            data class TextObject(
                @Json(name = "language")
                val language: String,
                @Json(name = "text")
                val text: String,
                @Json(name = "type")
                val type: String
            )

            @JsonClass(generateAdapter = true)
            data class Thumbnail(
                @Json(name = "extension")
                val extension: String,
                @Json(name = "path")
                val path: String
            )

            @JsonClass(generateAdapter = true)
            data class Url(
                @Json(name = "type")
                val type: String,
                @Json(name = "url")
                val url: String
            )

            @JsonClass(generateAdapter = true)
            data class Variant(
                @Json(name = "name")
                val name: String,
                @Json(name = "resourceURI")
                val resourceURI: String
            )
        }
    }
}
