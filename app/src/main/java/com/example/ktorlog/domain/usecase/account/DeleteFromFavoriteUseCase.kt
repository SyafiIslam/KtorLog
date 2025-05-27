package com.example.ktorlog.domain.usecase.account

import com.example.ktorlog.data.remote.request.favorite.DeleteFromFavoriteRequest
import com.example.ktorlog.data.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteFromFavoriteUseCase @Inject constructor(
    private val repository: AccountRepository
) {

    suspend operator fun invoke(request: DeleteFromFavoriteRequest) = repository.deleteFromFavorite(request)
}