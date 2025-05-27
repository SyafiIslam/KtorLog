package com.example.ktorlog.domain.usecase.tv

import com.example.ktorlog.data.repository.TvRepository
import javax.inject.Inject


class GetShowReviewsUseCase @Inject constructor(
    private val repository: TvRepository
) {

    suspend operator fun invoke(id: Int) = repository.getShowReview(id)
}