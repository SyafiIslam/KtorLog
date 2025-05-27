package com.example.ktorlog.domain.usecase.tv

import com.example.ktorlog.data.remote.response.list.FilmListResponse
import com.example.ktorlog.data.repository.TvRepository
import com.example.ktorlog.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetShowRecommendationUseCase @Inject constructor(
    private val repository: TvRepository
) {

    suspend operator fun invoke(id: Int)= repository.getTvRecommendation(id)

}