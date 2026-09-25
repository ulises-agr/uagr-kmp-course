/*
 * NetworkClient.ios.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.network.client

import com.uagr.kmp.course.data.local.datastore.AppDataStore
import com.uagr.kmp.course.utils.animation.constants.Constants
import com.uagr.kmp.course.utils.constants.NetworkUrl
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual fun createHttpClient(appDataStore: AppDataStore): HttpClient = HttpClient(engineFactory = Darwin) {

    install(plugin = HttpTimeout) {
        requestTimeoutMillis = Constants.REQUEST_TIMEOUT_MILLIS
        connectTimeoutMillis = Constants.CONNECT_TIMEOUT_MILLIS
        socketTimeoutMillis = Constants.SOCKET_TIMEOUT_MILLIS
    }

    install(plugin = Logging) {
        level = LogLevel.BODY
    }

    install(plugin = ContentNegotiation) {
        json(
            json = Json { ignoreUnknownKeys = true },
            contentType = ContentType.Application.Json,
        )
    }

    defaultRequest {
        url(urlString = NetworkUrl.BASE_URL)
    }
}
