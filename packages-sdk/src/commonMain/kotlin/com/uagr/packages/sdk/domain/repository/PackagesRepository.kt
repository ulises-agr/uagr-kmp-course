/*
 * PackagesRepository.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.domain.repository

import com.uagr.packages.sdk.domain.model.PackagesDataModel
import com.uagr.packages.sdk.domain.model.PackagesModel
import com.uagr.packages.sdk.internal.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface PackagesRepository {
    suspend fun getNetworkPackages(url: String): Flow<NetworkResult<PackagesModel>>
    suspend fun clearAndInsertPackages(packages: List<PackagesDataModel>): Flow<Unit>
    suspend fun getLocalPackages(): Flow<List<PackagesDataModel>>
}
