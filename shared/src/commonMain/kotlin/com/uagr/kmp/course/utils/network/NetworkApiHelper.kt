/*
 * NetworkApiHelper.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.utils.network

import com.uagr.kmp.course.data.network.model.response.base.BaseResponse
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.io.IOException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

suspend inline fun <reified Response : Any, Domain> safeApiCall(
    crossinline apiCall: suspend () -> HttpResponse,
    crossinline transform: (Response) -> Domain,
): NetworkResult<Domain> =
    try {
        val response = apiCall()
        if (response.status.isSuccess()) {
            val body = response.body<Response>()
            if (body is BaseResponse && body.success != true) {
                NetworkResult.Error(
                    message = body.message?.takeIf { message -> message.isNotBlank() } ?: "Error unknown",
                    errorType = NetworkErrorType.UNKNOWN,
                )
            } else {
                NetworkResult.Success(response = transform(body))
            }
        } else {
            response.toNetworkError()
        }
    } catch (exception: Exception) {
        when (exception) {
            is TimeoutCancellationException -> NetworkResult.Error(
                message = "Error timeout: ${exception.message}",
                errorType = NetworkErrorType.TIMEOUT,
            )
            is CancellationException -> throw exception
            is IOException -> NetworkResult.Error(
                message = "Error red: ${exception.message}",
                errorType = NetworkErrorType.NETWORK,
            )
            is ClientRequestException -> exception.response.toNetworkError()
            is ServerResponseException -> exception.response.toNetworkError()
            else -> NetworkResult.Error(
                message = "Error unknown: ${exception.message}",
                errorType = NetworkErrorType.UNKNOWN,
            )
        }
    }

@PublishedApi
internal suspend fun HttpResponse.toNetworkError(): NetworkResult.Error {
    val message = try {
        val body = Json.parseToJsonElement(bodyAsText()) as? JsonObject
        val error = body?.get("error") as? JsonObject
        val value = error?.get("message") as? JsonPrimitive
        value?.takeIf { it.isString }?.content?.takeIf { it.isNotBlank() }
    } catch (exception: CancellationException) {
        throw exception
    } catch (_: Exception) {
        null
    }
    return NetworkResult.Error(
        message = message ?: "Error HTTP: ${status.value}",
        code = status.value,
        errorType = NetworkErrorType.HTTP,
    )
}
