/*
 * MovementsNetworkDataSourceImpl.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.network.datasource.movements

import com.uagr.kmp.course.data.network.model.request.movements.MovementsRequest
import com.uagr.kmp.course.data.network.model.response.movements.MovementsResponse
import com.uagr.kmp.course.domain.mapper.movements.toDomain
import com.uagr.kmp.course.domain.model.movements.MovementsModel
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.network.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.koin.core.annotation.Factory

@Factory
class MovementsNetworkDataSourceImpl(
    private val httpClient: HttpClient,
) : MovementsNetworkDataSource {

    override suspend fun fetchMovementsData(
        url: String,
        request: MovementsRequest,
    ): NetworkResult<MovementsModel> =
        safeApiCall(
            apiCall = {
                httpClient.get(urlString = url) {
                    contentType(type = ContentType.Application.Json)
                }
            },
            transform = { data: MovementsResponse ->
                data.toDomain()
            },
        )
}
