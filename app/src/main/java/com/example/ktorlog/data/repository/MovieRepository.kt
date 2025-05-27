package com.example.ktorlog.data.repository

import android.os.Trace
import android.util.Log
import com.example.ktorlog.data.remote.request.rating.RatingRequest
import com.example.ktorlog.data.remote.response.detail.FilmDetailResponse
import com.example.ktorlog.data.remote.response.list.FilmListResponse
import com.example.ktorlog.data.remote.response.rating.RatingResponse
import com.example.ktorlog.data.remote.response.state.FilmStateResponse
import com.example.ktorlog.data.remote.response.trailer.FilmTrailerResponse
import com.example.ktorlog.domain.repository.IMovieRepository
import com.example.ktorlog.util.Resource
import com.example.ktorlog.data.remote.response.review.FilmReviewResponse
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

class MovieRepository(private val api: HttpClient) : IMovieRepository {

    private val movieEndpoint = "/movie"

    override suspend fun getSpecificMovieType(type: String): Flow<Resource<FilmListResponse>> =
        flow {
            emit(Resource.Loading())
            Trace.beginSection("API: getSpecificMovieType")

            val result =
                try {
                    val resp = api.get("$movieEndpoint/$type")
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

    override suspend fun getMovieDetail(id: Int): Flow<Resource<FilmDetailResponse>> =
        flow {
            emit(Resource.Loading())
            Trace.beginSection("API: getMovieDetail")

            val result =
                try {
                    val resp = api.get("$movieEndpoint/$id")
                        .body<FilmDetailResponse>()

                    Resource.Success(resp)
                } catch (e: RedirectResponseException) {
                    Resource.Error(e.response.status.description)
                } catch (e: ClientRequestException) {
                    Resource.Error(e.response.status.description)
                } catch (e: ServerResponseException) {
                    Resource.Error(e.response.status.description)
                } catch (e: Exception) {
                    Log.i("@@@", "getMovieDetail: ${e.message}")
                    Resource.Error(e.message.toString())
                } finally {
                    Trace.endSection()
                }

            emit(result)
        }

    override suspend fun getMovieRecommendation(id: Int): Flow<Resource<FilmListResponse>> =
        flow {
            emit(Resource.Loading())
            Trace.beginSection("API: getMovieRecommendation")

            val result =
                try {
                    val resp = api.get("$movieEndpoint/$id/recommendations")
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

    override suspend fun getMovieTrailer(id: Int): Flow<Resource<FilmTrailerResponse>> =
        flow {
            emit(Resource.Loading())
            Trace.beginSection("API: getMovieTrailer")

            val result =
                try {
                    val resp = api.get("$movieEndpoint/$id/videos")
                        .body<FilmTrailerResponse>()

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

    override suspend fun checkMovieState(id: Int): Flow<Resource<FilmStateResponse>> =
        flow {
            emit(Resource.Loading())
            Trace.beginSection("API: checkMovieState")

            val result =
                try {
                    val resp = api.get("$movieEndpoint/$id/account_states")
                        .body<FilmStateResponse>()

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

    override suspend fun getMovieReview(id: Int): Flow<Resource<FilmReviewResponse>> =
        flow {
            emit(Resource.Loading())
            Trace.beginSection("API: getMovieReview")

            val result =
                try {
                    val resp = api.get("$movieEndpoint/$id/reviews")
                        .body<FilmReviewResponse>()

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

    override suspend fun addMovieRating(
        id: Int,
        request: RatingRequest
    ): Flow<Resource<RatingResponse>> =
        flow {
            emit(Resource.Loading())
            Trace.beginSection("API: addMovieRating")

            val result =
                try {
                    val resp = api.post("$movieEndpoint/$id/rating") {
                        contentType(ContentType.Application.Json)
                        setBody(request)
                    }.body<RatingResponse>()

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
