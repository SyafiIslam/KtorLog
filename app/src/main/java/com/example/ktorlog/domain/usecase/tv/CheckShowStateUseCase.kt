package com.example.ktorlog.domain.usecase.tv

import com.example.ktorlog.data.repository.TvRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CheckShowStateUseCase @Inject constructor(
    private val repository: TvRepository
) {
    suspend operator fun invoke(id: Int)= repository.checkShowState(id)
}