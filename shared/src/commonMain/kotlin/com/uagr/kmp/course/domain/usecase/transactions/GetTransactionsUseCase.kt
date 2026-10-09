/*
 * GetTransactionsUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.domain.usecase.transactions

import com.uagr.kmp.course.domain.model.transaction.TransactionsModel
import com.uagr.kmp.course.domain.repository.transactions.TransactionsRepository
import com.uagr.kmp.course.utils.constant.NetworkUrl
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class GetTransactionsUseCase(
    private val transactionsRepository: TransactionsRepository,
) {

    suspend operator fun invoke(
        url: String = NetworkUrl.TRANSACTIONS_ENDPOINT,
        token: String,
    ): Flow<NetworkResult<TransactionsModel>> =
        transactionsRepository.getTransactions(
            url = url,
            token = token,
        )
}
