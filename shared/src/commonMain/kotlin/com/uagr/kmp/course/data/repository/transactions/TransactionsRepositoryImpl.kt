package com.uagr.kmp.course.data.repository.transactions

import com.uagr.kmp.course.data.local.datasource.transactions.TransactionsLocalDataSource
import com.uagr.kmp.course.data.network.datasource.transactions.TransactionsNetworkDataSource
import com.uagr.kmp.course.domain.model.transactions.TransactionsModel
import com.uagr.kmp.course.domain.repository.transactions.TransactionsRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TransactionsRepositoryImpl(
    private val transactionsNetworkDataSource: TransactionsNetworkDataSource,
    private val transactionsLocalDataSource: TransactionsLocalDataSource,
    private val dispatcher: CoroutineDispatcher,
): TransactionsRepository {

    override suspend fun callService(
        url: String, access_token: String
        ): Flow<NetworkResult<TransactionsModel>> = flow{
        emit(transactionsNetworkDataSource.getTransaction(
            url = url,
            access_token = access_token))
    }.flowOn(dispatcher)

    override suspend fun getTransactions(): TransactionsModel? {
        return transactionsLocalDataSource.getTransactions()
    }
}