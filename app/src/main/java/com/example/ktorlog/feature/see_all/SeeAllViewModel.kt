package com.example.ktorlog.feature.see_all

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ktorlog.data.remote.response.list.FilmData
import com.example.ktorlog.domain.usecase.movie.GetSpecificMovieTypeUseCase
import com.example.ktorlog.domain.usecase.tv.GetSpecificTvTypeUseCase
import com.example.ktorlog.util.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeeAllViewModel @Inject constructor(
    private val getSpecificMovieTypeUseCase: GetSpecificMovieTypeUseCase,
    private val getSpecificTvTypeUseCase: GetSpecificTvTypeUseCase
): ViewModel() {

    private val token= "Bearer ${Constant.TOKEN}"

    private val _isLoading= mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _nowPlayingMoviesList= mutableStateOf<List<FilmData>>(emptyList())
    val nowPlayingMoviesList: State<List<FilmData>> = _nowPlayingMoviesList

    private val _popularMoviesList= mutableStateOf<List<FilmData>>(emptyList())
    val popularMoviesList: State<List<FilmData>> = _popularMoviesList

    private val _topRatedMoviesList= mutableStateOf<List<FilmData>>(emptyList())
    val topRatedMoviesList: State<List<FilmData>> = _topRatedMoviesList

    private val _upcomingMoviesList= mutableStateOf<List<FilmData>>(emptyList())
    val upcomingMoviesList: State<List<FilmData>> = _upcomingMoviesList

    private val _airingTodayShowsList= mutableStateOf<List<FilmData>>(emptyList())
    val airingTodayShowsList: State<List<FilmData>> = _airingTodayShowsList

    private val _popularShowsList= mutableStateOf<List<FilmData>>(emptyList())
    val popularShowsList: State<List<FilmData>> = _popularShowsList

    private val _onTheAirShowsList= mutableStateOf<List<FilmData>>(emptyList())
    val onTheAirShowsList: State<List<FilmData>> = _onTheAirShowsList

    private val _topRatedShowsList= mutableStateOf<List<FilmData>>(emptyList())
    val topRatedShowsList: State<List<FilmData>> = _topRatedShowsList

    fun setLoadingState(state: Boolean) {
        _isLoading.value= state
    }

    fun setNowPlayingMoviesList(data: List<FilmData>) {
        _nowPlayingMoviesList.value= data
    }

    fun setPopularMoviesList(data: List<FilmData>) {
        _popularMoviesList.value= data
    }

    fun setTopRatedMovies(data: List<FilmData>) {
        _topRatedMoviesList.value= data
    }

    fun setUpcomingMovies(data: List<FilmData>) {
        _upcomingMoviesList.value= data
    }

    fun setAiringTodayShows(data: List<FilmData>) {
        _airingTodayShowsList.value= data
    }

    fun setPopularShows(data: List<FilmData>) {
        _popularShowsList.value= data
    }

    fun setOnTheAirShows(data: List<FilmData>) {
        _onTheAirShowsList.value= data
    }

    fun setTopRatedShows(data: List<FilmData>) {
        _topRatedShowsList.value= data
    }

    suspend fun getSpecificMovieType(type: String)= getSpecificMovieTypeUseCase(type)
    suspend fun getSpecificTvType(type: String)= getSpecificTvTypeUseCase(type)
}