package com.uagr.kmp.course.data.network.datasource.transactions

import com.uagr.kmp.course.data.network.model.request.transactions.TransactionsRequest
import com.uagr.kmp.course.domain.model.transactions.TransactionsModel
import com.uagr.kmp.course.utils.network.NetworkResult

interface TransactionsNetworkDataSource {
    suspend fun createTransaction(
        url: String,
        access_token: String,
        transactionRequest: TransactionsRequest
    ): NetworkResult<TransactionsModel>

    suspend fun getTransaction(
        url: String,
        access_token: String,
    ): NetworkResult<TransactionsModel>
}