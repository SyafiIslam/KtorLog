package com.example.ktorlog.feature.detail

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ktorlog.data.remote.request.favorite.AddToFavoriteRequest
import com.example.ktorlog.data.remote.request.favorite.DeleteFromFavoriteRequest
import com.example.ktorlog.data.remote.request.rating.RatingRequest
import com.example.ktorlog.data.remote.request.watchlist.AddToWatchlistRequest
import com.example.ktorlog.data.remote.request.watchlist.DeleteFromWatchlistRequest
import com.example.ktorlog.data.remote.response.detail.FilmDetailResponse
import com.example.ktorlog.data.remote.response.list.FilmData
import com.example.ktorlog.data.remote.response.review.Review
import com.example.ktorlog.data.remote.response.state.FilmStateResponse
import com.example.ktorlog.feature.component.Fab
import com.example.ktorlog.feature.component.LoadingDialog
import com.example.ktorlog.feature.detail.component.AddRatingDialog
import com.example.ktorlog.feature.detail.component.DetailHeader
import com.example.ktorlog.feature.detail.component.FilmInfo
import com.example.ktorlog.feature.detail.component.FilmOverview
import com.example.ktorlog.feature.detail.component.FilmRecommendation
import com.example.ktorlog.feature.detail.component.UserReviewBottomSheet
import com.example.ktorlog.theme.Neutral900
import com.example.ktorlog.util.Constant
import com.example.ktorlog.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FilmDetailScreen(
    navController: NavController,
    type: String,
    id: Int,
    viewModel: FilmDetailViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    val isLoading by viewModel.isLoading
    val filmDetail by viewModel.filmDetail
    val filmTrailer by viewModel.filmTrailer
    val userReview by viewModel.userReview

    val isFavorite = remember {
        mutableStateOf(false)
    }

    val onWatchlist = remember {
        mutableStateOf(false)
    }

    val isSheetOpen = remember {
        mutableStateOf(false)
    }

    val isDialogShown= remember {
        mutableStateOf(false)
    }


    LaunchedEffect(true) {

        getFilmDetail(viewModel, this, context, id, type)
        getFilmTrailer(viewModel, this, context, id, type)

        if (type == Constant.MOVIE) {
            checkMovieState(viewModel, this, context, id, isFavorite, onWatchlist)
            getMovieRecommendation(viewModel, this, context, id)
        } else {
            checkShowState(viewModel, this, context, id, isFavorite, onWatchlist)
            getShowRecommendation(viewModel, this, context, id)
        }
    }

    if (isDialogShown.value) {
        AddRatingDialog(
            onDialogDismiss = {
                isDialogShown.value= false
            },
            onClickSubmit = { rate ->
                if (rate > 0) {
                    val ratingRequest= RatingRequest(rate.toDouble())
                    addRating(viewModel, scope, context, id, type, ratingRequest)
                    isDialogShown.value= false
                }
            }
        )
    }

    if (isLoading) {
        LoadingDialog()
    }

    if (isSheetOpen.value) {
        UserReviewBottomSheet(
            onDismiss = {
                isSheetOpen.value= false
            },
            userReview
        )
    }

    Scaffold(
        floatingActionButton = {
            Fab(
                onClick = {
                    if (isFavorite.value) {
                        isFavorite.value = false
                        deleteFromFavorite(viewModel, scope, context, id, type)
                    } else {
                        isFavorite.value = true
                        addToFavorite(viewModel, scope, context, id, type)
                    }
                },
                isFavorite
            )
        }
    ) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .background(Neutral900)
        ) {

            item {
                filmDetail?.let {
                    DetailHeader(navController, it.backdrop_path, filmTrailer)
                }
            }

            item {
                FilmInfo(filmDetail, type)
            }

            item {
                FilmOverview(
                    filmDetail?.overview,
                    filmDetail?.vote_average,
                    onWatchlist,
                    onWatchlistClick = {

                        if (onWatchlist.value) {
                            onWatchlist.value = false
                            deleteFromWatchlist(viewModel, scope, context, id, type)
                        } else {
                            onWatchlist.value = true
                            addToWatchlist(viewModel, scope, context, id, type)
                        }
                    },
                    onButtonReviewClick = {
                        getUserReview(viewModel, scope, context, id, type)
                        isSheetOpen.value= true
                    },
                    onAddRatingClick = {
                        isDialogShown.value= true
                    }
                )
            }

            item {
                FilmRecommendation(navController, viewModel, type)
            }
        }
    }
}

private fun showToast(msg: String, context: Context) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

private fun getFilmDetail(
    viewModel: FilmDetailViewModel,
    scope: CoroutineScope,
    context: Context,
    id: Int,
    type: String
) {

    scope.launch {
        viewModel.getFilmDetail(id, type).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    viewModel.setFilmDetail(it.data as FilmDetailResponse)
                }
            }
        }
    }
}

private fun getFilmTrailer(
    viewModel: FilmDetailViewModel,
    scope: CoroutineScope,
    context: Context,
    id: Int,
    type: String
) {

    scope.launch {
        viewModel.getFilmTrailer(id, type).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> {
                    viewModel.setLoadingState(true)
                }
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    val trailerKey = it.data?.let { trailer ->
                        if (trailer.results.isNotEmpty()) {
                            trailer.results[0].key
                        } else {
                            ""
                        }
                    }
                    viewModel.setFilmTrailer(trailerKey ?: "")
                }
            }
        }
    }
}

private fun getMovieRecommendation(
    viewModel: FilmDetailViewModel,
    scope: CoroutineScope,
    context: Context,
    id: Int,
) {

    scope.launch {
        viewModel.getMovieRecommendation(id).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    viewModel.setFilmRecommendation(it.data?.results as List<FilmData>)
                }
            }
        }
    }
}

private fun getShowRecommendation(
    viewModel: FilmDetailViewModel,
    scope: CoroutineScope,
    context: Context,
    id: Int,
) {

    scope.launch {
        viewModel.getShowRecommendation(id).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    viewModel.setFilmRecommendation(it.data?.results as List<FilmData>)
                }
            }
        }
    }
}

private fun checkMovieState(
    viewModel: FilmDetailViewModel,
    scope: CoroutineScope,
    context: Context,
    id: Int,
    isFavorite: MutableState<Boolean>,
    onWatchlist: MutableState<Boolean>,
) {
    scope.launch {
        viewModel.checkMovieStates(id).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    isFavorite.value = it.data?.favorite as Boolean
                    onWatchlist.value = it.data.watchlist
                }
            }
        }
    }
}

private fun checkShowState(
    viewModel: FilmDetailViewModel,
    scope: CoroutineScope,
    context: Context,
    id: Int,
    isFavorite: MutableState<Boolean>,
    onWatchlist: MutableState<Boolean>,
) {
    scope.launch {

        viewModel.checkShowStates(id).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    isFavorite.value = it.data?.favorite as Boolean
                    onWatchlist.value = it.data.watchlist
                }
            }
        }
    }
}

private fun addToFavorite(
    viewModel: FilmDetailViewModel,
    scope: CoroutineScope,
    context: Context,
    id: Int,
    type: String
) {

    val request = AddToFavoriteRequest(
        media_type = type,
        media_id = id,
        favorite = true
    )
    scope.launch {
        viewModel.addToFavorite(request).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    Log.i("@@@", "addToFavorite: ")
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    showToast("Added to favorite", context)
                }
            }
        }
    }
}

private fun addToWatchlist(
    viewModel: FilmDetailViewModel,
    scope: CoroutineScope,
    context: Context,
    id: Int,
    type: String
) {

    val request = AddToWatchlistRequest(
        media_type = type,
        media_id = id,
        watchlist = true
    )
    scope.launch {
        viewModel.addToWatchlist(request).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    Log.i("@@@", "addToWatchlist: ")
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    showToast("Added to watchlist", context)
                }
            }
        }
    }
}

private fun deleteFromFavorite(
    viewModel: FilmDetailViewModel,
    scope: CoroutineScope,
    context: Context,
    id: Int,
    type: String
) {

    val request = DeleteFromFavoriteRequest(
        media_type = type,
        media_id = id,
        favorite = false
    )
    scope.launch {
        viewModel.deleteFromFavorite(request).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    Log.i("@@@", "deleteFromFavorite: ")
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    showToast("Deleted from favorite", context)
                }
            }
        }
    }
}

private fun deleteFromWatchlist(
    viewModel: FilmDetailViewModel,
    scope: CoroutineScope,
    context: Context,
    id: Int,
    type: String
) {

    val request = DeleteFromWatchlistRequest(
        media_type = type,
        media_id = id,
        watchlist = false
    )
    scope.launch {
        viewModel.deleteFromWatchlist(request).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    Log.i("@@@", "deleteFromWatchlist: ${it.message}")
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    showToast("Deleted from watchlist", context)
                }
            }
        }
    }
}

private fun getUserReview(
    viewModel: FilmDetailViewModel,
    scope: CoroutineScope,
    context: Context,
    id: Int,
    type: String
) {

    scope.launch {
        viewModel.getUserReview(id, type).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }
                is Resource.Loading -> {
                    viewModel.setLoadingState(true)
                }
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    viewModel.setUserReview(it.data?.results as List<Review>)
                }
            }
        }
    }
}

private fun addRating(
    viewModel: FilmDetailViewModel,
    scope: CoroutineScope,
    context: Context,
    id: Int,
    type: String,
    request: RatingRequest,
) {

    scope.launch {
        viewModel.addRating(id, type, request).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }
                is Resource.Loading -> {
                    viewModel.setLoadingState(true)
                }
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    showToast("Thank you for your rating", context)
                }
            }
        }
    }
}