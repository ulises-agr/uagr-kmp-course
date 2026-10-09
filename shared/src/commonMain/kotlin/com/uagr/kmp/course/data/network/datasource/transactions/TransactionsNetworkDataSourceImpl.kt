/*
 * TransactionsNetworkDataSourceImpl.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.network.datasource.transactions

import com.uagr.kmp.course.data.network.model.response.transaction.TransactionsResponse
import com.uagr.kmp.course.domain.mapper.transaction.toDomain
import com.uagr.kmp.course.domain.model.transaction.TransactionsModel
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.network.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.koin.core.annotation.Factory

@Factory
class TransactionsNetworkDataSourceImpl(
    private val httpClient: HttpClient,
) : TransactionsNetworkDataSource {

    override suspend fun getTransactions(
        url: String,
        token: String,
    ): NetworkResult<TransactionsModel> =
        safeApiCall(
            apiCall = {
                httpClient.get(urlString = url) {
                    contentType(type = ContentType.Application.Json)
                    bearerAuth(token = token)
                }
            },
            transform = { data: TransactionsResponse ->
                data.toDomain()
            },
        )
}
