package com.example.ktorlog.data.remote.response.state

import kotlinx.serialization.Serializable

@Serializable
data class FilmStateResponse(
    val favorite: Boolean = false,
    val id: Int = 0,
    val watchlist: Boolean = false
)
