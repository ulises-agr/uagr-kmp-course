/*
 * LoginNetworkDataSourceImpl.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.network.datasource.login

import com.uagr.kmp.course.data.network.model.request.login.loginRequest
import com.uagr.kmp.course.data.network.model.response.login.loginResponse
import com.uagr.kmp.course.domain.mapper.login.toDomain
import com.uagr.kmp.course.domain.model.login.loginModel
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.network.safeApiCallDirect
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.koin.core.annotation.Factory

@Factory
class LoginNetworkDataSourceImpl(
    private val httpClient: HttpClient,
) : LoginNetworkDataSource {

    override suspend fun login(
        url: String,
        loginRequest: loginRequest,
    ): NetworkResult<loginModel> =
        safeApiCallDirect(
            apiCall = {
                httpClient.post(urlString = url) {
                    contentType(type = ContentType.Application.Json)
                    setBody(body = loginRequest)
                }
            },
            transform = { data: loginResponse ->
                data.toDomain()
            },
        )
}
