package com.example.ktorlog.domain.repository

import com.example.ktorlog.data.remote.request.rating.RatingRequest
import com.example.ktorlog.data.remote.response.detail.FilmDetailResponse
import com.example.ktorlog.data.remote.response.list.FilmListResponse
import com.example.ktorlog.data.remote.response.rating.RatingResponse
import com.example.ktorlog.data.remote.response.state.FilmStateResponse
import com.example.ktorlog.data.remote.response.trailer.FilmTrailerResponse
import com.example.ktorlog.util.Resource
import com.example.ktorlog.data.remote.response.review.FilmReviewResponse
import kotlinx.coroutines.flow.Flow

interface ITvRepository {

    suspend fun getSpecificTvType(type: String): Flow<Resource<FilmListResponse>>
    suspend fun getTvDetail(id: Int): Flow<Resource<FilmDetailResponse>>
    suspend fun getTvRecommendation(id: Int): Flow<Resource<FilmListResponse>>
    suspend fun getTvTrailer(id: Int): Flow<Resource<FilmTrailerResponse>>
    suspend fun checkShowState(id: Int): Flow<Resource<FilmStateResponse>>
    suspend fun getShowReview(id: Int): Flow<Resource<FilmReviewResponse>>
    suspend fun addShowRating(id: Int, request: RatingRequest): Flow<Resource<RatingResponse>>
}