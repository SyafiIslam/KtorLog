package com.example.ktorlog.data.remote

import com.example.ktorlog.util.Constant
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object ApiConfig {
    val ktorClient= HttpClient(Android) {
        install(ContentNegotiation) {
            json(json = Json {
                ignoreUnknownKeys= true
                prettyPrint= true
                coerceInputValues= true
            })
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(DefaultRequest) {
            url {
                protocol = URLProtocol.HTTPS
                host = Constant.BASE_URL
            }
            header(HttpHeaders.Authorization, "Bearer ${Constant.TOKEN}")
            header(HttpHeaders.Accept, ContentType.Application.Json)
            contentType(ContentType.Application.Json)
        }
    }
}