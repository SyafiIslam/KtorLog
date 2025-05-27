package com.example.ktorlog.data.remote.response.list

import kotlinx.serialization.Serializable

@Serializable
data class FilmListResponse(
    val dates: Dates = Dates(),
    val page: Int = 0,
    val results: List<FilmData> = emptyList(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)
