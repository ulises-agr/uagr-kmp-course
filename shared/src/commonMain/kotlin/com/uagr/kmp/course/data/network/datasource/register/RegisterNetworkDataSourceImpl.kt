/*
 * RegisterNetworkDataSourceImpl.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.repository.register

import com.uagr.kmp.course.data.network.datasource.register.RegisterNetworkDataSource
import com.uagr.kmp.course.data.network.model.request.register.RegisterRequest
import com.uagr.kmp.course.data.network.model.response.register.RegisterResponse
import com.uagr.kmp.course.domain.mapper.register.toDomain
import com.uagr.kmp.course.domain.model.register.RegisterModel
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.network.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.koin.core.annotation.Factory

@Factory
class RegisterNetworkDataSourceImpl(
    private val httpClient: HttpClient,
) : RegisterNetworkDataSource {

    override suspend fun register(
        url: String,
        request: RegisterRequest,
    ): NetworkResult<RegisterModel> =
        safeApiCall(
            apiCall = {
                httpClient.post(urlString = url) {
                    contentType(type = ContentType.Application.Json)
                    setBody(body = request)
                }
            },
            transform = { data: RegisterResponse ->
                data.toDomain()
            },
        )
}