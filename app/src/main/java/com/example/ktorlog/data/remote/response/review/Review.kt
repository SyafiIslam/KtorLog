package com.example.ktorlog.data.remote.response.review

import com.example.ktorlog.data.remote.response.review.AuthorDetails
import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val author: String= "",
    val author_details: AuthorDetails= AuthorDetails(),
    val content: String= "",
    val created_at: String= "",
    val id: String= "",
    val updated_at: String= "",
    val url: String= ""
)