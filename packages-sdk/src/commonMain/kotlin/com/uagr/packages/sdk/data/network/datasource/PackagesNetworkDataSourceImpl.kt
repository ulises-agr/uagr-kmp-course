/*
 * PackagesNetworkDataSourceImpl.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.data.network.datasource

import com.uagr.packages.sdk.data.network.model.PackagesResponse
import com.uagr.packages.sdk.domain.mapper.toDomain
import com.uagr.packages.sdk.domain.model.PackagesModel
import com.uagr.packages.sdk.internal.network.NetworkResult
import com.uagr.packages.sdk.internal.network.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class PackagesNetworkDataSourceImpl(
    private val httpClient: HttpClient,
) : PackagesNetworkDataSource {

    override suspend fun getPackages(url: String): NetworkResult<PackagesModel> =
        safeApiCall(
            apiCall = {
                httpClient.get(urlString = url) {
                    contentType(type = ContentType.Application.Json)
                }
            },
            transform = { data: PackagesResponse ->
                data.toDomain()
            },
        )
}
