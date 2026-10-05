/*
 * HomeNetworkDataSourceImpl.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.network.datasource.home

import com.uagr.kmp.course.data.network.model.request.home.HomeRequest
import com.uagr.kmp.course.data.network.model.response.home.HomeResponse
import com.uagr.kmp.course.domain.mapper.home.toDomain
import com.uagr.kmp.course.domain.model.home.HomeModel
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.network.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.koin.core.annotation.Factory

@Factory
class HomeNetworkDataSourceImpl(
    private val httpClient: HttpClient,
) : HomeNetworkDataSource {

    override suspend fun fetchHomeData(
        url: String,
        request: HomeRequest,
    ): NetworkResult<HomeModel> =
        safeApiCall(
            apiCall = {
                httpClient.get(urlString = url) {
                    contentType(type = ContentType.Application.Json)
                }
            },
            transform = { data: HomeResponse ->
                data.toDomain()
            },
        )
}
