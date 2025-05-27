package com.example.ktorlog.data.remote.response.detail

import kotlinx.serialization.Serializable

@Serializable
data class ProductionCompany(
    val id: Int= 0,
    val logo_path: String= "",
    val name: String= "",
    val origin_country: String= ""
)