package com.example.ktorlog.data.remote.request.watchlist

import kotlinx.serialization.Serializable

@Serializable
data class DeleteFromWatchlistRequest (
    val media_type: String,
    val media_id: Int,
    val watchlist: Boolean,
)