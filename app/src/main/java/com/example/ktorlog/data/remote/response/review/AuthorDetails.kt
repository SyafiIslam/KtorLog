package com.example.ktorlog.data.remote.response.review

import kotlinx.serialization.Serializable

@Serializable
data class AuthorDetails(
    val avatar_path: String= "",
    val name: String= "",
    val rating: Double= 0.0,
    val username: String= ""
)