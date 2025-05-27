package com.example.ktorlog.data.remote.request.favorite

import kotlinx.serialization.Serializable

@Serializable
data class AddToFavoriteRequest (
    val media_type: String,
    val media_id: Int,
    val favorite: Boolean,
)