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

interface IMovieRepository {

    suspend fun getSpecificMovieType(type: String): Flow<Resource<FilmListResponse>>
    suspend fun getMovieDetail(id: Int): Flow<Resource<FilmDetailResponse>>
    suspend fun getMovieRecommendation(id: Int): Flow<Resource<FilmListResponse>>
    suspend fun getMovieTrailer(id: Int): Flow<Resource<FilmTrailerResponse>>
    suspend fun checkMovieState(id: Int): Flow<Resource<FilmStateResponse>>
    suspend fun getMovieReview(id: Int): Flow<Resource<FilmReviewResponse>>
    suspend fun addMovieRating(id: Int, request: RatingRequest): Flow<Resource<RatingResponse>>
}