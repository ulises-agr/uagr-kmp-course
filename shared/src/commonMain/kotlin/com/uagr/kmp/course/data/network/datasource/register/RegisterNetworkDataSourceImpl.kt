/*
 * RegisterNetworkDataSourceImpl.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.repository.register

import com.uagr.kmp.course.data.network.datasource.register.RegisterNetworkDataSource
import com.uagr.kmp.course.data.network.model.request.register.RegisterRequest
import com.uagr.kmp.course.data.network.model.response.register.RegisterResponse
import com.uagr.kmp.course.utils.constant.NetworkUrl
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class RegisterNetworkDataSourceImpl(
    private val httpClient: HttpClient
) : RegisterNetworkDataSource {

    override suspend fun register(request: RegisterRequest): RegisterResponse {
        return httpClient.post(NetworkUrl.REGISTER_ENDPOINT) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }
}