/*
 * TransactionsRepositoryImpl.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.repository.transactions

import com.uagr.kmp.course.data.network.datasource.transactions.TransactionsNetworkDataSource
import com.uagr.kmp.course.domain.model.transaction.TransactionsModel
import com.uagr.kmp.course.domain.repository.transactions.TransactionsRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory

@Factory
class TransactionsRepositoryImpl(
    private val transactionsNetworkDataSource: TransactionsNetworkDataSource,
    private val dispatcher: CoroutineDispatcher,
) : TransactionsRepository {

    override suspend fun getTransactions(
        url: String,
        token: String,
    ): Flow<NetworkResult<TransactionsModel>> = flow {
        emit(
            transactionsNetworkDataSource.getTransactions(
                url = url,
                token = token,
            )
        )
    }.flowOn(context = dispatcher)
}
