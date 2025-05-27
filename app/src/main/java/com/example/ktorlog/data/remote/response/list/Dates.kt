package com.example.ktorlog.data.remote.response.list

import kotlinx.serialization.Serializable

@Serializable
data class Dates(
    val maximum: String= "",
    val minimum: String= ""
)