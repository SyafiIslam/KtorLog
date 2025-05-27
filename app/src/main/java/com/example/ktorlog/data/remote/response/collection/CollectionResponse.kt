package com.example.ktorlog.data.remote.response.collection

import kotlinx.serialization.Serializable

@Serializable
data class CollectionResponse(
    val status_code: Int= 0,
    val status_message: String= "",
    val success: Boolean= false
)