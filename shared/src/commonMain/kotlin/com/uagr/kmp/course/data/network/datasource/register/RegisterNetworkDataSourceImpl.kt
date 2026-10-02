package com.uagr.kmp.course.data.network.datasource.register

import com.uagr.kmp.course.data.network.model.request.RegisterRequest
import com.uagr.kmp.course.data.network.model.response.register.RegisterResponse
import com.uagr.kmp.course.domain.mapper.register.toDomain
import com.uagr.kmp.course.domain.model.register.RegisterModel
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.network.NetworkErrorType
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull

/*
// RegisterNetworkDataSourceImpl.kt
// Created by Karla Judith Cervantes Higareda on 29/09/26.
// Copyright (c) 2026. All rights reserved.
*/

class RegisterNetworkDataSourceImpl(
    private val httpClient: HttpClient,
) : RegisterNetworkDataSource {
    override suspend fun createRegister(url: String, request: RegisterRequest): NetworkResult<RegisterModel> =
        try {
            // FinTrack returns user/tokens directly, without the BaseResponse envelope.
            val response = httpClient.post(urlString = url) {
                contentType(ContentType.Application.Json)
                setBody(request)
            }
            if (response.status.isSuccess()) {
                NetworkResult.Success(response.body<RegisterResponse>().toDomain())
            } else {
                response.toRegisterError()
            }
        } catch (exception: CancellationException) {
            throw exception
        } catch (exception: ResponseException) {
            exception.response.toRegisterError()
        }
}

private suspend fun HttpResponse.toRegisterError(): NetworkResult.Error {
    val text = bodyAsText()
    val body = runCatching { Json.parseToJsonElement(text) as? JsonObject }.getOrNull()
    val detail = body?.get("detail") ?: body?.get("message")
    val message = when (detail) {
        is JsonPrimitive -> detail.contentOrNull
        is JsonArray -> detail.mapNotNull {
            ((it as? JsonObject)?.get("msg") as? JsonPrimitive)?.contentOrNull
        }.joinToString("\n")
        else -> null
    }
    return NetworkResult.Error(
        message = message?.takeIf { it.isNotBlank() } ?: "Error HTTP: ${status.value}",
        code = status.value,
        errorType = NetworkErrorType.HTTP,
    )
}
