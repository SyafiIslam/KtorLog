package com.example.ktorlog.domain.usecase.account

import com.example.ktorlog.data.remote.request.watchlist.AddToWatchlistRequest
import com.example.ktorlog.data.repository.AccountRepository
import javax.inject.Inject

class AddToWatchListUseCase @Inject constructor(
    private val repository: AccountRepository
) {

    suspend operator fun invoke(request: AddToWatchlistRequest)= repository.addToWatchlist(request)
}