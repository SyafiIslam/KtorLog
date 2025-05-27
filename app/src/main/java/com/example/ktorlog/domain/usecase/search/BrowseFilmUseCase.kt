package com.example.ktorlog.domain.usecase.search

import com.example.ktorlog.data.repository.SearchRepository
import javax.inject.Inject

class BrowseFilmUseCase @Inject constructor(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(query: String, type: String) = repository.browseMovies(query, type)
}