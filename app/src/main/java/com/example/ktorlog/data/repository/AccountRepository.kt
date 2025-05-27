package com.example.ktorlog.data.repository

import android.os.Trace
import com.example.ktorlog.data.remote.request.favorite.AddToFavoriteRequest
import com.example.ktorlog.data.remote.request.favorite.DeleteFromFavoriteRequest
import com.example.ktorlog.data.remote.request.watchlist.AddToWatchlistRequest
import com.example.ktorlog.data.remote.request.watchlist.DeleteFromWatchlistRequest
import com.example.ktorlog.data.remote.response.collection.CollectionResponse
import com.example.ktorlog.data.remote.response.list.FilmListResponse
import com.example.ktorlog.domain.repository.IAccountRepository
import com.example.ktorlog.util.Constant
import com.example.ktorlog.util.Resource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AccountRepository(
    private val api: HttpClient
): IAccountRepository {

    private val accountEndpoint = "/account"
    private val userId = Constant.ACCOUNT_ID

    override suspend fun addToFavorite(
        addToFavoriteRequest: AddToFavoriteRequest
    ): Flow<Resource<CollectionResponse>> =
        flow {
            emit(Resource.Loading())
            Trace.beginSection("API: addToFavorite")

            val result =
                try {
                    val resp = api.post("$accountEndpoint/$userId/favorite") {
                        contentType(ContentType.Application.Json)
                        setBody(addToFavoriteRequest)
                    }.body<CollectionResponse>()

                    Resource.Success(resp)
                } catch (e: RedirectResponseException) {
                    Resource.Error(e.response.status.description)
                } catch (e: ClientRequestException) {
                    Resource.Error(e.response.status.description)
                } catch (e: ServerResponseException) {
                    Resource.Error(e.response.status.description)
                } catch (e: Exception) {
                    Resource.Error(e.message.toString())
                } finally {
                    Trace.endSection()
                }

            emit(result)
        }

    override suspend fun deleteFromFavorite(
        deleteFromFavoriteRequest: DeleteFromFavoriteRequest
    ): Flow<Resource<CollectionResponse>> =
        flow {
            emit(Resource.Loading())
            Trace.beginSection("API: deleteFromFavorite")

            val result =
                try {
                    val resp = api.post("$accountEndpoint/$userId/favorite") {
                        contentType(ContentType.Application.Json)
                        setBody(deleteFromFavoriteRequest)
                    }.body<CollectionResponse>()

                    Resource.Success(resp)
                } catch (e: RedirectResponseException) {
                    Resource.Error(e.response.status.description)
                } catch (e: ClientRequestException) {
                    Resource.Error(e.response.status.description)
                } catch (e: ServerResponseException) {
                    Resource.Error(e.response.status.description)
                } catch (e: Exception) {
                    Resource.Error(e.message.toString())
                } finally {
                    Trace.endSection()
                }

            emit(result)
        }

    override suspend fun addToWatchlist(
        addToWatchlistRequest: AddToWatchlistRequest
    ): Flow<Resource<CollectionResponse>> =
        flow {
            emit(Resource.Loading())
            Trace.beginSection("API: addToWatchlist")

            val result =
                try {
                    val resp = api.post("$accountEndpoint/$userId/watchlist") {
                        contentType(ContentType.Application.Json)
                        setBody(addToWatchlistRequest)
                    }.body<CollectionResponse>()

                    Resource.Success(resp)
                } catch (e: RedirectResponseException) {
                    Resource.Error(e.response.status.description)
                } catch (e: ClientRequestException) {
                    Resource.Error(e.response.status.description)
                } catch (e: ServerResponseException) {
                    Resource.Error(e.response.status.description)
                } catch (e: Exception) {
                    Resource.Error(e.message.toString())
                } finally {
                    Trace.endSection()
                }

            emit(result)
        }

    override suspend fun deleteFromWatchlist(
        deleteFromWatchlistRequest: DeleteFromWatchlistRequest
    ): Flow<Resource<CollectionResponse>> =
        flow {
            emit(Resource.Loading())
            Trace.beginSection("API: deleteFromWatchlist")

            val result =
                try {
                    val resp = api.post("$accountEndpoint/$userId/watchlist") {
                        contentType(ContentType.Application.Json)
                        setBody(deleteFromWatchlistRequest)
                    }.body<CollectionResponse>()

                    Resource.Success(resp)
                } catch (e: RedirectResponseException) {
                    Resource.Error(e.response.status.description)
                } catch (e: ClientRequestException) {
                    Resource.Error(e.response.status.description)
                } catch (e: ServerResponseException) {
                    Resource.Error(e.response.status.description)
                } catch (e: Exception) {
                    Resource.Error(e.message.toString())
                } finally {
                    Trace.endSection()
                }

            emit(result)
        }

    override suspend fun getFavorite(type: String): Flow<Resource<FilmListResponse>> =
        flow {
            emit(Resource.Loading())
            Trace.beginSection("API: getFavorite")

            val result =
                try {
                    val resp = api.get("$accountEndpoint/$userId/favorite/$type")
                        .body<FilmListResponse>()

                    Resource.Success(resp)
                } catch (e: RedirectResponseException) {
                    Resource.Error(e.response.status.description)
                } catch (e: ClientRequestException) {
                    Resource.Error(e.response.status.description)
                } catch (e: ServerResponseException) {
                    Resource.Error(e.response.status.description)
                } catch (e: Exception) {
                    Resource.Error(e.message.toString())
                } finally {
                    Trace.endSection()
                }

            emit(result)
        }

    override suspend fun getWatchlist(type: String): Flow<Resource<FilmListResponse>> =
        flow {
            emit(Resource.Loading())
            Trace.beginSection("API: getWatchlist")

            val result =
                try {
                    val resp = api.get("$accountEndpoint/$userId/watchlist/$type")
                        .body<FilmListResponse>()

                    Resource.Success(resp)
                } catch (e: RedirectResponseException) {
                    Resource.Error(e.response.status.description)
                } catch (e: ClientRequestException) {
                    Resource.Error(e.response.status.description)
                } catch (e: ServerResponseException) {
                    Resource.Error(e.response.status.description)
                } catch (e: Exception) {
                    Resource.Error(e.message.toString())
                } finally {
                    Trace.endSection()
                }

            emit(result)
        }
}