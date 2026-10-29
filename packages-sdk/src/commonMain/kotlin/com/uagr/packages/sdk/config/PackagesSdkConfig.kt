/*
 * PackagesSdkConfig.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.config

/**
 * Runtime configuration for the Packages SDK.
 *
 * @property baseUrl API root, e.g. `https://server-dummy-hitss.onrender.com`
 * @property productsEndpoint Relative path appended to [baseUrl]
 * @property tokenProvider Supplies access/refresh tokens per request (host-owned)
 * @property requestTimeoutMillis Ktor request timeout
 * @property connectTimeoutMillis Ktor connect timeout
 * @property socketTimeoutMillis Ktor socket timeout
 */
data class PackagesSdkConfig(
    val baseUrl: String,
    val productsEndpoint: String = DEFAULT_PRODUCTS_ENDPOINT,
    val tokenProvider: PackagesTokenProvider,
    val requestTimeoutMillis: Long = 15_000L,
    val connectTimeoutMillis: Long = 10_000L,
    val socketTimeoutMillis: Long = 10_000L,
) {
    val productsUrl: String
        get() {
            val base = baseUrl.trimEnd('/')
            val path = productsEndpoint.trimStart('/')
            return "$base/$path"
        }

    companion object {
        const val DEFAULT_PRODUCTS_ENDPOINT = "api/v1/products"
    }
}

fun interface PackagesTokenProvider {
    suspend fun getTokens(): PackagesTokens?
}

data class PackagesTokens(
    val accessToken: String,
    val refreshToken: String = "",
)
