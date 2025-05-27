package com.example.ktorlog.domain.usecase.tv

import com.example.ktorlog.data.repository.TvRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSpecificTvTypeUseCase @Inject constructor(
    private val repository: TvRepository
) {

    suspend operator fun invoke(type: String)= repository.getSpecificTvType(type)
}