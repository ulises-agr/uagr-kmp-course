/*
 * PackagesNetworkDataSource.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.network.datasource

import com.uagr.kmp.course.domain.model.PackagesModel
import com.uagr.kmp.course.utils.network.NetworkResult

interface PackagesNetworkDataSource {
    suspend fun getPackages(url: String): NetworkResult<PackagesModel>
}
