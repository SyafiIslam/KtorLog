package com.example.ktorlog.data.remote.request.rating

import kotlinx.serialization.Serializable

@Serializable
data class RatingRequest(
    val value: Double= 0.0
)