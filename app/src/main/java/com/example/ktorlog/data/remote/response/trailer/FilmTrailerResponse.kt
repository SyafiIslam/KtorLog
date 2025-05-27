package com.example.ktorlog.data.remote.response.trailer

import kotlinx.serialization.Serializable

@Serializable
data class FilmTrailerResponse(
    val id: Int= 0,
    val results: List<Trailer> = emptyList()
)