package com.example.ktorlog.data.remote.response.detail

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val id: Int = 0,
    val name: String = ""
)
