/*
 * BudgetNetworkDataSourceImpl.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.network.datasource.budget

import com.uagr.kmp.course.data.network.model.request.budget.BudgetRequest
import com.uagr.kmp.course.data.network.model.response.budget.BudgetResponse
import com.uagr.kmp.course.domain.mapper.budget.toDomain
import com.uagr.kmp.course.domain.model.budget.BudgetModel
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.network.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.koin.core.annotation.Factory

@Factory
class BudgetNetworkDataSourceImpl(
    private val httpClient: HttpClient,
) : BudgetNetworkDataSource {

    override suspend fun fetchBudgetData(
        url: String,
        request: BudgetRequest,
    ): NetworkResult<BudgetModel> =
        safeApiCall(
            apiCall = {
                httpClient.get(urlString = url) {
                    contentType(type = ContentType.Application.Json)
                }
            },
            transform = { data: BudgetResponse ->
                data.toDomain()
            },
        )
}
