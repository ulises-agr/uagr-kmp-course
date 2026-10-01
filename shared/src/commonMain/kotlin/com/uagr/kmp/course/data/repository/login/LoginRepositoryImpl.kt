package com.uagr.kmp.course.data.repository.login

import com.uagr.kmp.course.data.local.datastore.AppDataStore
import com.uagr.kmp.course.data.network.model.login.LoginRequest
import com.uagr.kmp.course.data.network.model.login.LoginResponse
import com.uagr.kmp.course.domain.repository.login.LoginRepository
import com.uagr.kmp.course.utils.constant.NetworkUrl
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess

class LoginRepositoryImpl(
    private val httpClient: HttpClient,
    private val appDataStore: AppDataStore
) : LoginRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Boolean {

        val deviceId = appDataStore.getOrCreateDeviceId()

        val response = httpClient.post(NetworkUrl.LOGIN_ENDPOINT) {
            contentType(ContentType.Application.Json)

            setBody(
                LoginRequest(
                    email = email,
                    password = password,
                    deviceId = deviceId
                )
            )
        }

        if (!response.status.isSuccess()) {
            println("LOGIN ERROR HTTP -> ${response.status}")
            return false
        }

        val loginResponse = response.body<LoginResponse>()

        appDataStore.saveUserToken(loginResponse.accessToken)

        return true
    }
}