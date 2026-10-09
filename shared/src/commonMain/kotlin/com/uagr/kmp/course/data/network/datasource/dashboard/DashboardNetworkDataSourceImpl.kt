/*
 * DashboardNetworkDataSourceImpl.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.network.datasource.dashboard

import com.uagr.kmp.course.data.network.model.response.dashboard.DashboardResponse
import com.uagr.kmp.course.domain.mapper.dashboard.toDomain
import com.uagr.kmp.course.domain.model.dashboard.DashboardModel
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.network.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.koin.core.annotation.Factory

@Factory
class DashboardNetworkDataSourceImpl(
    private val httpClient: HttpClient,
) : DashboardNetworkDataSource {

    override suspend fun getDashboard(
        url: String,
        token: String,
    ): NetworkResult<DashboardModel> =
        safeApiCall(
            apiCall = {
                httpClient.get(urlString = url) {
                    contentType(type = ContentType.Application.Json)
                    bearerAuth(token = token)
                }
            },
            transform = { data: DashboardResponse ->
                data.toDomain()
            },
        )
}
