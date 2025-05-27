package com.example.ktorlog.data.repository

import android.os.Trace
import com.example.ktorlog.data.remote.request.rating.RatingRequest
import com.example.ktorlog.data.remote.response.detail.FilmDetailResponse
import com.example.ktorlog.data.remote.response.list.FilmListResponse
import com.example.ktorlog.data.remote.response.rating.RatingResponse
import com.example.ktorlog.data.remote.response.state.FilmStateResponse
import com.example.ktorlog.data.remote.response.trailer.FilmTrailerResponse
import com.example.ktorlog.domain.repository.ITvRepository
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

class TvRepository(
    private val api: HttpClient
) : ITvRepository {

    private val tvEndpoint = "/tv"

    override suspend fun getSpecificTvType(type: String): Flow<Resource<FilmListResponse>> =
        flow {
            emit(Resource.Loading())
            Trace.beginSection("API: getSpecificTvType")

            val result =
                try {
                    val resp = api.get("$tvEndpoint/$type")
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

    override suspend fun getTvDetail(id: Int): Flow<Resource<FilmDetailResponse>> =
        flow {
            emit(Resource.Loading())
            Trace.beginSection("API: getTvDetail")

            val result =
                try {
                    val resp = api.get("$tvEndpoint/$id")
                        .body<FilmDetailResponse>()

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

    override suspend fun getTvRecommendation(id: Int): Flow<Resource<FilmListResponse>> =
        flow {
            emit(Resource.Loading())
            Trace.beginSection("API: getTvRecommendation")

            val result =
                try {
                    val resp = api.get("$tvEndpoint/$id/recommendations")
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

    override suspend fun getTvTrailer(id: Int): Flow<Resource<FilmTrailerResponse>> =
        flow {
            emit(Resource.Loading())
            Trace.beginSection("API: getTvTrailer")

            val result =
                try {
                    val resp = api.get("$tvEndpoint/$id/videos")
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

    override suspend fun checkShowState(id: Int): Flow<Resource<FilmStateResponse>> =
        flow {
            emit(Resource.Loading())
            Trace.beginSection("API: checkShowState")

            val result =
                try {
                    val resp = api.get("$tvEndpoint/$id/account_states")
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

    override suspend fun getShowReview(id: Int): Flow<Resource<FilmReviewResponse>> =
        flow {
            emit(Resource.Loading())
            Trace.beginSection("API: getShowReview")

            val result =
                try {
                    val resp = api.get("$tvEndpoint/$id/reviews")
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

    override suspend fun addShowRating(
        id: Int,
        request: RatingRequest
    ): Flow<Resource<RatingResponse>> =
        flow {
            emit(Resource.Loading())
            Trace.beginSection("API: addShowRating")

            val result =
                try {
                    val resp = api.post("$tvEndpoint/$id/rating") {
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