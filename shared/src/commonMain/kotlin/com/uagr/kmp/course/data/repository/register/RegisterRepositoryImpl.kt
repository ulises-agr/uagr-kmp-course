package com.uagr.kmp.course.data.repository.register

import com.uagr.kmp.course.data.local.datastore.AppDataStore
import com.uagr.kmp.course.domain.repository.register.RegisterRepository
import io.ktor.client.HttpClient
import com.uagr.kmp.course.data.network.model.response.register.RegisterResponse
import com.uagr.kmp.course.data.network.model.request.register.RegisterRequest
import com.uagr.kmp.course.utils.constant.NetworkUrl
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess

class RegisterRepositoryImpl(
    private val httpClient: HttpClient,
    private val appDataStore: AppDataStore
) : RegisterRepository {

    override suspend fun register(
        name: String,
        email: String,
        password: String
    ): Boolean {

        val response = httpClient.post(NetworkUrl.REGISTER_ENDPOINT) {
            contentType(ContentType.Application.Json)

            setBody(
                RegisterRequest(
                    name = name,
                    email = email,
                    password = password
                )
            )
        }

        if (!response.status.isSuccess()) {
            println("Register Error -> ${response.status}")
            return false
        }

        val registerResponse = response.body<RegisterResponse>()

        appDataStore.saveUserToken(
            registerResponse.tokens.accessToken
        )

        return true
    }
}