package com.uagr.kmp.course.data.network.datasource.transactions

import com.uagr.kmp.course.data.network.model.request.transactions.TransactionsRequest
import com.uagr.kmp.course.data.network.model.response.transactions.TransactionsResponse
import com.uagr.kmp.course.domain.mapper.transactions.toDomain
import com.uagr.kmp.course.domain.model.transactions.TransactionsModel
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.network.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class TransactionsNetworkDataSourceImpl(
    private val httpClient: HttpClient,
): TransactionsNetworkDataSource {
    override suspend fun createTransaction(
        url: String,
        access_token: String,
        transactionRequest: TransactionsRequest
    ): NetworkResult<TransactionsModel> =
        safeApiCall(
            apiCall = {
                httpClient.post(url) {
                    contentType(ContentType.Application.Json)
                    setBody(transactionRequest)
                }
            },
            transform = { data: TransactionsResponse ->
                data.toDomain()
            },
        )

    override suspend fun getTransaction(
        url: String,
        access_token: String,
    ): NetworkResult<TransactionsModel> =
        safeApiCall(
            apiCall = {
                 httpClient.get(url) {
                    contentType(ContentType.Application.Json)
                    header("Authorization", "Bearer $access_token")
                    parameter("page", 1)
                    parameter("page_size", 20)
                }
            },
            transform = { data: TransactionsResponse ->
                data.toDomain()
            },
        )
}