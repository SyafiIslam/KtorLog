package com.example.ktorlog.domain.usecase.account

import com.example.ktorlog.data.remote.request.favorite.AddToFavoriteRequest
import com.example.ktorlog.data.repository.AccountRepository
import javax.inject.Inject

class AddToFavoriteUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    suspend operator fun invoke(request: AddToFavoriteRequest) = repository.addToFavorite(request)
}