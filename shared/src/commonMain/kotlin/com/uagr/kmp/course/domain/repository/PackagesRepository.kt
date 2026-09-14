/*
 * PackagesRepository.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.repository

import com.uagr.kmp.course.domain.model.PackageModel
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface PackagesRepository {
    
    suspend fun getNetworkPackages(url : String) : Flow<NetworkResult<PackageModel>>
    
    suspend fun clearAndInsertPackages(packageModel: PackageModel) : Flow<Unit>
    
    suspend fun getLocalPackages() : Flow<PackageModel?>
    
}