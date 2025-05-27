package com.example.ktorlog.feature.collection

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.ktorlog.data.remote.request.favorite.DeleteFromFavoriteRequest
import com.example.ktorlog.data.remote.request.watchlist.DeleteFromWatchlistRequest
import com.example.ktorlog.data.remote.response.list.FilmData
import com.example.ktorlog.domain.usecase.account.DeleteFromFavoriteUseCase
import com.example.ktorlog.domain.usecase.account.DeleteFromWatchListUseCase
import com.example.ktorlog.domain.usecase.account.GetFavoriteFilmUseCase
import com.example.ktorlog.domain.usecase.account.GetWatchListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val getFavoriteFilmUseCase: GetFavoriteFilmUseCase,
    private val getWatchListUseCase: GetWatchListUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
    private val deleteFromWatchListUseCase: DeleteFromWatchListUseCase
): ViewModel() {

    private val _isLoading= mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _filmList= mutableStateOf<List<FilmData>>(emptyList())
    val filmList: State<List<FilmData>> = _filmList

    fun setLoadingState(state: Boolean) {
        _isLoading.value= state
    }

    fun setFilmList(data: List<FilmData>) {
        _filmList.value= data
    }

    suspend fun getFavoriteFilm(type: String)= getFavoriteFilmUseCase(type)
    suspend fun getWatchlist(type: String)= getWatchListUseCase(type)
    suspend fun deleteFromFavorite(request: DeleteFromFavoriteRequest)= deleteFromFavoriteUseCase(request)
    suspend fun deleteFromWatchlist(request: DeleteFromWatchlistRequest)= deleteFromWatchListUseCase(request)
}