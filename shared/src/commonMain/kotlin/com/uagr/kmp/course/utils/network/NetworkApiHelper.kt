/*
 * NetworkApiHelper.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.utils.network

import com.uagr.kmp.course.data.network.model.response.base.ApiErrorResponse
import com.uagr.kmp.course.data.network.model.response.base.BaseResponse
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.io.IOException

suspend inline fun <reified Response : BaseResponse, Domain> safeApiCall(
    crossinline apiCall: suspend () -> HttpResponse,
    crossinline transform: (Response) -> Domain,
): NetworkResult<Domain> =
    try {
        val response = apiCall()
        val status = response.status.value
        if (response.status.isSuccess()) {
            val body = response.body<Response>()
            NetworkResult.Success(response = transform(body))
        } else {
            val errorMessage = parseApiErrorMessage(response)
            NetworkResult.Error(
                message = errorMessage,
                code = status,
                errorType = NetworkErrorType.HTTP,
            )
        }
    } catch (exception: Exception) {
        exception.printStackTrace()
        when (exception) {
            is TimeoutCancellationException -> NetworkResult.Error(
                message = "Error timeout: ${exception.message}",
                errorType = NetworkErrorType.TIMEOUT,
            )
            is IOException -> NetworkResult.Error(
                message = "Error red: ${exception.message}",
                errorType = NetworkErrorType.NETWORK,
            )
            is ClientRequestException -> {
                val errorMessage = parseApiErrorMessage(exception.response)
                NetworkResult.Error(
                    message = errorMessage,
                    code = exception.response.status.value,
                    errorType = NetworkErrorType.HTTP,
                )
            }
            is ServerResponseException -> {
                val errorMessage = parseApiErrorMessage(exception.response)
                NetworkResult.Error(
                    message = errorMessage,
                    code = exception.response.status.value,
                    errorType = NetworkErrorType.HTTP,
                )
            }
            else -> NetworkResult.Error(
                message = "Error unknown: ${exception.message}",
                errorType = NetworkErrorType.UNKNOWN,
            )
        }
    }

suspend fun parseApiErrorMessage(response: HttpResponse): String {
    return try {
        val errorResponse = response.body<ApiErrorResponse>()
        errorResponse.parseErrorMessage() ?: "Error HTTP: ${response.status.value}"
    } catch (e: Exception) {
        "Error HTTP: ${response.status.value}"
    }
}
