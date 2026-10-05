/*
 * GoalsNetworkDataSourceImpl.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.network.datasource.goals

import com.uagr.kmp.course.data.network.model.request.goals.GoalsRequest
import com.uagr.kmp.course.data.network.model.response.goals.GoalsResponse
import com.uagr.kmp.course.domain.mapper.goals.toDomain
import com.uagr.kmp.course.domain.model.goals.GoalsModel
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.network.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.koin.core.annotation.Factory

@Factory
class GoalsNetworkDataSourceImpl(
    private val httpClient: HttpClient,
) : GoalsNetworkDataSource {

    override suspend fun fetchGoalsData(
        url: String,
        request: GoalsRequest,
    ): NetworkResult<GoalsModel> =
        safeApiCall(
            apiCall = {
                httpClient.get(urlString = url) {
                    contentType(type = ContentType.Application.Json)
                }
            },
            transform = { data: GoalsResponse ->
                data.toDomain()
            },
        )
}
