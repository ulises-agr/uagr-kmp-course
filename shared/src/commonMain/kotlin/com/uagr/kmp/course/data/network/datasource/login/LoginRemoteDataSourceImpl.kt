/*
 * LoginRemoteDataSourceImpl.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.network.datasource.login

import com.uagr.kmp.course.data.network.model.response.login.LoginDataResponse
import com.uagr.kmp.course.domain.mappers.login.toDomain
import com.uagr.kmp.course.domain.mappers.login.toRequest
import com.uagr.kmp.course.domain.model.login.LoginCredentialsModel
import com.uagr.kmp.course.domain.model.login.LoginDataModel
import com.uagr.kmp.course.utils.network.api.NetworkResult
import com.uagr.kmp.course.utils.network.api.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class LoginRemoteDataSourceImpl(
    private val httpClient: HttpClient,
) : LoginRemoteDataSource {

    override suspend fun login(
        url: String,
        loginCredentialsModel: LoginCredentialsModel,
    ): NetworkResult<LoginDataModel> = safeApiCall(
        apiCall = {
            httpClient.post(urlString = url) {
                contentType(type = ContentType.Application.Json)
                setBody(body = loginCredentialsModel.toRequest())
            }
        },
        transform = { data: LoginDataResponse ->
            data.toDomain()
        },
    )
}
