package com.uagr.kmp.course.domain.repository.transactions

import com.uagr.kmp.course.domain.model.transactions.TransactionsModel
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun callService(
        url: String,
    ): Flow<NetworkResult<TransactionsModel>>
    suspend fun getTransactions(): TransactionsModel?
}