/*
 * PackagesLocalDataSource.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.data.local.datasource

import com.uagr.packages.sdk.domain.model.PackagesDataModel

interface PackagesLocalDataSource {
    suspend fun clearAndInsertPackages(packages: List<PackagesDataModel>)
    suspend fun getPackages(): List<PackagesDataModel>
}
