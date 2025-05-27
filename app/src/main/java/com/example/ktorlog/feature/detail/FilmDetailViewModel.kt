package com.example.ktorlog.feature.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ktorlog.data.remote.request.favorite.AddToFavoriteRequest
import com.example.ktorlog.data.remote.request.favorite.DeleteFromFavoriteRequest
import com.example.ktorlog.data.remote.request.rating.RatingRequest
import com.example.ktorlog.data.remote.request.watchlist.AddToWatchlistRequest
import com.example.ktorlog.data.remote.request.watchlist.DeleteFromWatchlistRequest
import com.example.ktorlog.data.remote.response.detail.FilmDetailResponse
import com.example.ktorlog.data.remote.response.list.FilmData
import com.example.ktorlog.data.remote.response.state.FilmStateResponse
import com.example.ktorlog.domain.usecase.account.AddToFavoriteUseCase
import com.example.ktorlog.domain.usecase.account.AddToWatchListUseCase
import com.example.ktorlog.domain.usecase.account.DeleteFromFavoriteUseCase
import com.example.ktorlog.domain.usecase.account.DeleteFromWatchListUseCase
import com.example.ktorlog.domain.usecase.movie.AddMovieRatingUseCase
import com.example.ktorlog.domain.usecase.movie.CheckMovieStateUseCase
import com.example.ktorlog.domain.usecase.movie.GetMovieDetailUseCase
import com.example.ktorlog.domain.usecase.movie.GetMovieRecommendationUseCase
import com.example.ktorlog.domain.usecase.movie.GetMovieReviewsUseCase
import com.example.ktorlog.domain.usecase.movie.GetMovieTrailerUseCase
import com.example.ktorlog.domain.usecase.tv.AddShowRatingUseCase
import com.example.ktorlog.domain.usecase.tv.CheckShowStateUseCase
import com.example.ktorlog.domain.usecase.tv.GetShowDetailUseCase
import com.example.ktorlog.domain.usecase.tv.GetShowRecommendationUseCase
import com.example.ktorlog.domain.usecase.tv.GetShowReviewsUseCase
import com.example.ktorlog.domain.usecase.tv.GetShowTrailerUseCase
import com.example.ktorlog.data.remote.response.review.Review
import com.example.ktorlog.util.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilmDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getShowDetailUseCase: GetShowDetailUseCase,
    private val getMovieRecommendationUseCase: GetMovieRecommendationUseCase,
    private val getShowRecommendationUseCase: GetShowRecommendationUseCase,
    private val checkMovieStateUseCase: CheckMovieStateUseCase,
    private val checkShowStateUseCase: CheckShowStateUseCase,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val addToWatchListUseCase: AddToWatchListUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
    private val deleteFromWatchListUseCase: DeleteFromWatchListUseCase,
    private val getMovieTrailerUseCase: GetMovieTrailerUseCase,
    private val getShowTrailerUseCase: GetShowTrailerUseCase,
    private val getMovieReviewsUseCase: GetMovieReviewsUseCase,
    private val getShowReviewsUseCase: GetShowReviewsUseCase,
    private val addMovieRatingUseCase: AddMovieRatingUseCase,
    private val addShowRatingUseCase: AddShowRatingUseCase
) : ViewModel() {

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _filmDetail = mutableStateOf<FilmDetailResponse?>(null)
    val filmDetail: State<FilmDetailResponse?> = _filmDetail

    private val _filmRecommendation= mutableStateOf<List<FilmData>>(emptyList())
    val filmRecommendation: State<List<FilmData>> = _filmRecommendation

    private val _filmState= mutableStateOf<FilmStateResponse?>(null)
    val filmState: State<FilmStateResponse?> = _filmState

    private val _filmTrailer= mutableStateOf("")
    val filmTrailer: State<String> = _filmTrailer

    private val _userReview= mutableStateOf<List<Review>>(emptyList())
    val userReview: State<List<Review>> = _userReview

    fun setLoadingState(state: Boolean) {
        _isLoading.value = state
    }

    fun setFilmDetail(data: FilmDetailResponse) {
        _filmDetail.value = data
    }

    fun setFilmRecommendation(data: List<FilmData>) {
        _filmRecommendation.value= data
    }

    fun setFilmState(state: FilmStateResponse) {
        _filmState.value= state
    }

    suspend fun getFilmDetail(id: Int, type: String) =
        if (type == Constant.MOVIE) {
            getMovieDetailUseCase(id)
        } else {
            getShowDetailUseCase(id)
        }

    suspend fun getFilmTrailer(id: Int, type: String) =
        if (type == Constant.MOVIE) {
            getMovieTrailerUseCase(id)
        } else {
            getShowTrailerUseCase(id)
        }

    suspend fun getUserReview(id: Int, type: String) =
        if (type == Constant.MOVIE) {
            getMovieReviewsUseCase(id)
        } else {
            getShowReviewsUseCase(id)
        }

    suspend fun addRating(id: Int, type: String, request: RatingRequest) =
        if (type == Constant.MOVIE) {
            addMovieRatingUseCase(id, request)
        } else {
            addShowRatingUseCase(id, request)
        }

    fun setFilmTrailer(trailer: String) {
        _filmTrailer.value= trailer
    }

    fun setUserReview(review: List<Review>) {
        _userReview.value= review
    }

    suspend fun getMovieRecommendation(id: Int)= getMovieRecommendationUseCase(id)
    suspend fun getShowRecommendation(id: Int)= getShowRecommendationUseCase(id)
    suspend fun checkMovieStates(id: Int)= checkMovieStateUseCase(id)
    suspend fun checkShowStates(id: Int)= checkShowStateUseCase(id)
    suspend fun addToFavorite(request: AddToFavoriteRequest)= addToFavoriteUseCase(request)
    suspend fun addToWatchlist(request: AddToWatchlistRequest)= addToWatchListUseCase(request)
    suspend fun deleteFromFavorite(request: DeleteFromFavoriteRequest)= deleteFromFavoriteUseCase(request)
    suspend fun deleteFromWatchlist(request: DeleteFromWatchlistRequest)= deleteFromWatchListUseCase(request)
}