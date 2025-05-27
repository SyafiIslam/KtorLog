package com.example.ktorlog.data.remote.response.rating

import kotlinx.serialization.Serializable

@Serializable
data class RatingResponse(
    val status_code: Int= 0,
    val status_message: String= "",
    val success: Boolean= false
)