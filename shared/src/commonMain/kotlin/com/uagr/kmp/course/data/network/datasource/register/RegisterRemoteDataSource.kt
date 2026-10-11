package com.uagr.kmp.course.data.network.datasource.register

import com.uagr.kmp.course.data.network.model.request.register.RegisterRequest
import com.uagr.kmp.course.utils.constant.NetworkUrl
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

class RegisterRemoteDataSource(
    private val httpClient: HttpClient
) {

    suspend fun register(
        request: RegisterRequest
    ): HttpResponse {
        return httpClient.post(NetworkUrl.REGISTER_ENDPOINT) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }
}