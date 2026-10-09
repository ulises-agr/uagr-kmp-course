/*
 * TransactionsRepository.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.domain.repository.transactions

import com.uagr.kmp.course.domain.model.transaction.TransactionsModel
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface TransactionsRepository {
    suspend fun getTransactions(
        url: String,
        token: String,
    ): Flow<NetworkResult<TransactionsModel>>
}
