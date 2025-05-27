package com.example.ktorlog.domain.usecase.movie

import com.example.ktorlog.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CheckMovieStateUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(id: Int)= repository.checkMovieState(id)
}