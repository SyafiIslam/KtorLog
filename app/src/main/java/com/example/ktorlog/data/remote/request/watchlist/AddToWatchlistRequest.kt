package com.example.ktorlog.data.remote.request.watchlist

import kotlinx.serialization.Serializable

@Serializable
data class AddToWatchlistRequest (
    val media_type: String,
    val media_id: Int,
    val watchlist: Boolean,
)