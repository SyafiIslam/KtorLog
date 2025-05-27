package com.example.ktorlog.feature.see_all

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ktorlog.data.remote.response.list.FilmData
import com.example.ktorlog.feature.component.EmptyStateCard
import com.example.ktorlog.feature.component.FilmCard
import com.example.ktorlog.feature.home.showToast
import com.example.ktorlog.feature.see_all.component.SeeAllTopBar
import com.example.ktorlog.util.Constant
import com.example.ktorlog.util.Resource
import com.example.ktorlog.util.Route
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SeeAllScreen(
    navController: NavController,
    type: String,
    category: String,
    viewModel: SeeAllViewModel= hiltViewModel()
) {

    val context = LocalContext.current

    val isLoading by viewModel.isLoading

    val nowPlayingMovieList by viewModel.nowPlayingMoviesList
    val popularMoviesList by viewModel.popularMoviesList
    val upcomingMoviesList by viewModel.upcomingMoviesList
    val topRatedMoviesList by viewModel.topRatedMoviesList

    val airingTodayShowsList by viewModel.airingTodayShowsList
    val popularShowsList by viewModel.popularShowsList
    val onTheAirShowsList by viewModel.onTheAirShowsList
    val topRatedShowsList by viewModel.topRatedShowsList

    LaunchedEffect(true) {

        if (type == Constant.TV) {
            when (category) {
                Constant.NOW_AIRING -> getAiringTodayShows(viewModel, this, context)
                Constant.POPULAR -> getPopularShows(viewModel, this, context)
                Constant.ON_THE_AIR -> getOnTheAirShows(viewModel, this, context)
                Constant.TOP_RATED -> getTopRatedShows(viewModel, this, context)
            }
        } else {
            when (category) {
                Constant.NOW_PLAYING -> getNowPlayingMovies(viewModel, this, context)
                Constant.POPULAR -> getPopularMovies(viewModel, this, context)
                Constant.UPCOMING -> getUpcomingMovies(viewModel, this, context)
                Constant.TOP_RATED -> getTopRatedMovies(viewModel, this, context)
            }
        }
    }

    Column(Modifier.fillMaxSize()) {
        SeeAllTopBar(navController, type, category)

        LazyVerticalGrid(
            columns = GridCells.Adaptive(150.dp),
            Modifier.padding(top= 16.dp, start = 16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            if (isLoading) {
                items(2) {
                    EmptyStateCard(
                        Modifier
                            .width(150.dp)
                            .height(250.dp)
                            .padding(end = 16.dp)
                    )
                }
            } else {
                items(
                    if (type == Constant.TV) {
                        when (category) {
                            Constant.NOW_AIRING -> airingTodayShowsList
                            Constant.POPULAR -> popularShowsList
                            Constant.ON_THE_AIR -> onTheAirShowsList
                            Constant.TOP_RATED -> topRatedShowsList
                            else -> airingTodayShowsList
                        }
                    } else {
                        when (category) {
                            Constant.NOW_PLAYING -> nowPlayingMovieList
                            Constant.POPULAR -> popularMoviesList
                            Constant.UPCOMING -> upcomingMoviesList
                            Constant.TOP_RATED -> topRatedMoviesList
                            else -> nowPlayingMovieList
                        }
                    }
                ) {
                    FilmCard(
                        Modifier
                            .width(150.dp)
                            .height(250.dp)
                            .padding(8.dp)
                            .wrapContentSize(Alignment.Center),
                        onClick = {
                            navController.navigate(Route.FilmDetailScreen(type, it.id))
                        },
                        imagePath = it.poster_path
                    )
                }
            }
        }
    }
}

fun getPopularMovies(
    viewModel: SeeAllViewModel,
    coroutineScope: CoroutineScope,
    context: Context
) {
    coroutineScope.launch {
        viewModel.getSpecificMovieType(Constant.POPULAR).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    viewModel.setPopularMoviesList(it.data?.results as List<FilmData>)
                }
            }
        }
    }
}

private fun getNowPlayingMovies(
    viewModel: SeeAllViewModel,
    coroutineScope: CoroutineScope,
    context: Context
) {
    coroutineScope.launch {
        viewModel.getSpecificMovieType(Constant.NOW_PLAYING).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    viewModel.setNowPlayingMoviesList(it.data?.results as List<FilmData>)
                }
            }
        }
    }
}

private fun getTopRatedMovies(
    viewModel: SeeAllViewModel,
    coroutineScope: CoroutineScope,
    context: Context
) {
    coroutineScope.launch {
        viewModel.getSpecificMovieType(Constant.TOP_RATED).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    viewModel.setTopRatedMovies(it.data?.results as List<FilmData>)
                }
            }
        }
    }
}

private fun getUpcomingMovies(
    viewModel: SeeAllViewModel,
    coroutineScope: CoroutineScope,
    context: Context
) {
    coroutineScope.launch {
        viewModel.getSpecificMovieType(Constant.UPCOMING).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    viewModel.setUpcomingMovies(it.data?.results as List<FilmData>)
                }
            }
        }
    }
}

private fun getAiringTodayShows(
    viewModel: SeeAllViewModel,
    coroutineScope: CoroutineScope,
    context: Context
) {
    coroutineScope.launch {
        viewModel.getSpecificTvType(Constant.NOW_AIRING).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    viewModel.setAiringTodayShows(it.data?.results as List<FilmData>)
                }
            }
        }
    }
}

private fun getPopularShows(
    viewModel: SeeAllViewModel,
    coroutineScope: CoroutineScope,
    context: Context
) {
    coroutineScope.launch {
        viewModel.getSpecificTvType(Constant.POPULAR).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    viewModel.setPopularShows(it.data?.results as List<FilmData>)
                }
            }
        }
    }
}

private fun getOnTheAirShows(
    viewModel: SeeAllViewModel,
    coroutineScope: CoroutineScope,
    context: Context
) {
    coroutineScope.launch {
        viewModel.getSpecificTvType(Constant.ON_THE_AIR).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    Log.i("@@@", "getOnTheAirShows: ${it.data?.results}")
                    viewModel.setOnTheAirShows(it.data?.results as List<FilmData>)
                }
            }
        }
    }
}

private fun getTopRatedShows(
    viewModel: SeeAllViewModel,
    coroutineScope: CoroutineScope,
    context: Context
) {
    coroutineScope.launch {
        viewModel.getSpecificTvType(Constant.TOP_RATED).collect {
            when (it) {
                is Resource.Error -> {
                    viewModel.setLoadingState(false)
                    showToast(it.message.toString(), context)
                }

                is Resource.Loading -> viewModel.setLoadingState(true)
                is Resource.Success -> {
                    viewModel.setLoadingState(false)
                    viewModel.setTopRatedShows(it.data?.results as List<FilmData>)
                }
            }
        }
    }
}
