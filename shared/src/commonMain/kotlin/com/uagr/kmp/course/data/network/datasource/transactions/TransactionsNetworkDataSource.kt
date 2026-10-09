/*
 * TransactionsNetworkDataSource.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.network.datasource.transactions

import com.uagr.kmp.course.domain.model.transaction.TransactionsModel
import com.uagr.kmp.course.utils.network.NetworkResult

interface TransactionsNetworkDataSource {
    suspend fun getTransactions(
        url: String,
        token: String,
    ): NetworkResult<TransactionsModel>
}
