package com.example.ktorlog.domain.usecase.account

import com.example.ktorlog.data.remote.request.watchlist.DeleteFromWatchlistRequest
import com.example.ktorlog.data.repository.AccountRepository
import javax.inject.Inject

class DeleteFromWatchListUseCase @Inject constructor(
    private val repository: AccountRepository
) {

    suspend operator fun invoke(request: DeleteFromWatchlistRequest)= repository
        .deleteFromWatchlist(request)
}