package com.example.ktorlog.domain.usecase.movie

import com.example.ktorlog.data.repository.MovieRepository
import javax.inject.Inject


class GetMovieReviewsUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(id: Int) = repository.getMovieReview(id)
}