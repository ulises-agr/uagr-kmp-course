
package com.uagr.kmp.course.utils.network

import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.io.IOException

suspend inline fun <reified Response : Any, Domain> safeApiCall(
    crossinline apiCall: suspend () -> HttpResponse,
    crossinline transform: (Response) -> Domain,
): NetworkResult<Domain> =
    try {
        val response = apiCall()
        val status = response.status.value

        if (response.status.isSuccess()) {
            val body = response.body<Response>()

            NetworkResult.Success(
                response = transform(body)
            )
        } else {
            NetworkResult.Error(
                message = "Error HTTP: $status",
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

            is CancellationException -> throw exception

            is IOException -> NetworkResult.Error(
                message = "Error red: ${exception.message}",
                errorType = NetworkErrorType.NETWORK,
            )

            is ClientRequestException -> NetworkResult.Error(
                message = "Error client (${exception.response.status.value}): ${exception.message}",
                code = exception.response.status.value,
                errorType = NetworkErrorType.HTTP,
            )

            is ServerResponseException -> NetworkResult.Error(
                message = "Error server (${exception.response.status.value}): ${exception.message}",
                code = exception.response.status.value,
                errorType = NetworkErrorType.HTTP,
            )

            else -> NetworkResult.Error(
                message = "Error unknown: ${exception.message}",
                errorType = NetworkErrorType.UNKNOWN,
            )
        }
    }
