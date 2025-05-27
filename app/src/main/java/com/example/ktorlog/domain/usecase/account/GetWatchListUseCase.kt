package com.example.ktorlog.domain.usecase.account

import com.example.ktorlog.data.repository.AccountRepository
import javax.inject.Inject

class GetWatchListUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    suspend operator fun invoke(type: String)= repository.getWatchlist(type)
}