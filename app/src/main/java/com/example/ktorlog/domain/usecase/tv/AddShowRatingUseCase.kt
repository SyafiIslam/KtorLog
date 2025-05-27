package com.example.ktorlog.domain.usecase.tv

import com.example.ktorlog.data.remote.request.rating.RatingRequest
import com.example.ktorlog.data.repository.MovieRepository
import com.example.ktorlog.data.repository.TvRepository
import javax.inject.Inject


class AddShowRatingUseCase @Inject constructor(
    private val repository: TvRepository
) {

    suspend operator fun invoke(id: Int, ratingRequest: RatingRequest)=
        repository.addShowRating(id, ratingRequest)
}