package com.uagr.kmp.course.data.network.datasource.login

import com.uagr.kmp.course.data.local.datasource.bone.LoginNetworkDataSource
import com.uagr.kmp.course.data.network.model.request.login.LoginRequest
import com.uagr.kmp.course.data.network.model.response.login.LoginResponse
import com.uagr.kmp.course.domain.mapper.bone.toDomain
import com.uagr.kmp.course.domain.model.login.TokensModel
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.network.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class LoginNetworkDataSourceImpl(
    private val httpClient: HttpClient,
): LoginNetworkDataSource {
    override suspend fun login(
        url: String,
        loginRequest: LoginRequest,
    ): NetworkResult<TokensModel> =
        safeApiCall(
            apiCall = {
                httpClient.post(url) {
                    contentType(ContentType.Application.Json)
                    setBody(loginRequest)
                }
            },
            transform = { data: LoginResponse ->
                data.toDomain()
            },
        )
}