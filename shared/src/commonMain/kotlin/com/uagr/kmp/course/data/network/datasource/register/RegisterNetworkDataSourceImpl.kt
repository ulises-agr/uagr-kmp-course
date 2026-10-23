package com.uagr.kmp.course.data.network.datasource.register

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

class RegisterNetworkDataSourceImpl(
    private val httpClient: HttpClient,
): RegisterNetworkDataSource {
    override suspend fun register(
        url: String,
        registerRequest: RegisterRequest
    ): NetworkResult<RegisterModel> =
        safeApiCall(
            apiCall = {
                httpClient.post(url) {
                    contentType(ContentType.Application.Json)
                    setBody(registerRequest)
                }
            },
            transform = { data: RegisterResponse ->
                data.toDomain()
            },
        )
}