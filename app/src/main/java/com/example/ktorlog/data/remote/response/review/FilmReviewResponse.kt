package com.example.ktorlog.data.remote.response.review

import kotlinx.serialization.Serializable

@Serializable
data class FilmReviewResponse(
    val id: Int= 0,
    val page: Int= 0,
    val results: List<Review> = emptyList(),
    val total_pages: Int= 0,
    val total_results: Int= 0
)