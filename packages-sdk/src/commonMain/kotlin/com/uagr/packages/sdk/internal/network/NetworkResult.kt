/*
 * NetworkResult.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.internal.network

sealed class NetworkResult<out T> {
    data class Success<T>(val response: T) : NetworkResult<T>()
    data class Error(
        val message: String,
        val code: Int? = null,
        val errorType: NetworkErrorType = NetworkErrorType.UNKNOWN,
    ) : NetworkResult<Nothing>()
}

enum class NetworkErrorType {
    TIMEOUT,
    NETWORK,
    HTTP,
    UNKNOWN,
}
