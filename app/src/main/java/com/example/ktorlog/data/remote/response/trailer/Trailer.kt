package com.example.ktorlog.data.remote.response.trailer

import kotlinx.serialization.Serializable

@Serializable
data class Trailer(
    val id: String= "",
    val iso_3166_1: String= "",
    val iso_639_1: String= "",
    val key: String= "",
    val name: String= "",
    val official: Boolean= false,
    val published_at: String= "",
    val site: String= "",
    val size: Int= 0,
    val type: String= ""
)