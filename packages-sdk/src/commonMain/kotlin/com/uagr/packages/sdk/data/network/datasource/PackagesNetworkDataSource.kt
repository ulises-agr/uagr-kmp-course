/*
 * PackagesNetworkDataSource.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.data.network.datasource

import com.uagr.packages.sdk.domain.model.PackagesModel
import com.uagr.packages.sdk.internal.network.NetworkResult

interface PackagesNetworkDataSource {
    suspend fun getPackages(url: String): NetworkResult<PackagesModel>
}
