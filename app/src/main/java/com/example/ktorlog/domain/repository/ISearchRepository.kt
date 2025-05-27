package com.example.ktorlog.domain.repository

import com.example.ktorlog.data.remote.response.collection.CollectionResponse
import com.example.ktorlog.data.remote.response.list.FilmListResponse
import com.example.ktorlog.util.Resource
import kotlinx.coroutines.flow.Flow

interface ISearchRepository {

    suspend fun browseMovies(query: String, type: String): Flow<Resource<FilmListResponse>>
}