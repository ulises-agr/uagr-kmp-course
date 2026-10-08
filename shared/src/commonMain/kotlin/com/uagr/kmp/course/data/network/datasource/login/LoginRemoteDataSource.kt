package com.uagr.kmp.course.data.network.datasource.login

import com.uagr.kmp.course.data.network.model.request.login.LoginRequest
import com.uagr.kmp.course.utils.constant.NetworkUrl
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.client.statement.HttpResponse

class LoginRemoteDataSource(
    private val httpClient: HttpClient
) {

    suspend fun login(
        request: LoginRequest
    ): HttpResponse {
        return httpClient.post(NetworkUrl.LOGIN_ENDPOINT) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }
}