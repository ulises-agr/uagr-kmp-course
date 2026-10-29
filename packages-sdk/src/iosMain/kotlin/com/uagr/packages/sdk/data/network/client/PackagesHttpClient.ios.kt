/*
 * PackagesHttpClient.ios.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.data.network.client

import com.uagr.packages.sdk.config.PackagesSdkConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual fun createPackagesHttpClient(config: PackagesSdkConfig): HttpClient =
    HttpClient(engineFactory = Darwin) {
        install(plugin = HttpTimeout) {
            requestTimeoutMillis = config.requestTimeoutMillis
            connectTimeoutMillis = config.connectTimeoutMillis
            socketTimeoutMillis = config.socketTimeoutMillis
        }

        install(plugin = Auth) {
            bearer {
                loadTokens {
                    config.tokenProvider.getTokens()?.let { tokens ->
                        BearerTokens(
                            accessToken = tokens.accessToken,
                            refreshToken = tokens.refreshToken,
                        )
                    }
                }
            }
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
            url(urlString = config.baseUrl.trimEnd('/') + "/")
        }
    }
