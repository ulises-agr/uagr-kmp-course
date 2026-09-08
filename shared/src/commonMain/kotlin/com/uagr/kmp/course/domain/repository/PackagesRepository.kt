/*
 * PackagesRepository.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.repository

import com.uagr.kmp.course.domain.model.PackagesModel
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface PackagesRepository {
    suspend fun getPackages(url: String): Flow<NetworkResult<PackagesModel>>
}
