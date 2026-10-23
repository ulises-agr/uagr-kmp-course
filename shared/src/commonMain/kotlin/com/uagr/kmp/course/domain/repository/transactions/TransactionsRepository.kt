package com.uagr.kmp.course.domain.repository.transactions

import com.uagr.kmp.course.domain.model.transactions.TransactionsModel
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface TransactionsRepository {
    suspend fun callService(
        url: String, access_token: String
    ): Flow<NetworkResult<TransactionsModel>>
    suspend fun getTransactions(): TransactionsModel?
}