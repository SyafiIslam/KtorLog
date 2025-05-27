package com.example.ktorlog.domain.repository

import com.example.ktorlog.data.remote.request.favorite.AddToFavoriteRequest
import com.example.ktorlog.data.remote.request.favorite.DeleteFromFavoriteRequest
import com.example.ktorlog.data.remote.request.watchlist.AddToWatchlistRequest
import com.example.ktorlog.data.remote.request.watchlist.DeleteFromWatchlistRequest
import com.example.ktorlog.data.remote.response.collection.CollectionResponse
import com.example.ktorlog.data.remote.response.list.FilmListResponse
import com.example.ktorlog.util.Resource
import kotlinx.coroutines.flow.Flow

interface IAccountRepository {

    suspend fun addToFavorite(
        addToFavoriteRequest: AddToFavoriteRequest
    ): Flow<Resource<CollectionResponse>>

    suspend fun deleteFromFavorite(
        deleteFromFavoriteRequest: DeleteFromFavoriteRequest
    ): Flow<Resource<CollectionResponse>>

    suspend fun addToWatchlist(
        addToWatchlistRequest: AddToWatchlistRequest
    ): Flow<Resource<CollectionResponse>>

    suspend fun deleteFromWatchlist(
        deleteFromWatchlistRequest: DeleteFromWatchlistRequest
    ): Flow<Resource<CollectionResponse>>

    suspend fun getFavorite(
        type: String,
    ): Flow<Resource<FilmListResponse>>

    suspend fun getWatchlist(
        type: String,
    ): Flow<Resource<FilmListResponse>>
}