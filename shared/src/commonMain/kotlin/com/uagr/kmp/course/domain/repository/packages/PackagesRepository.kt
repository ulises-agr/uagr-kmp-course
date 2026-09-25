/*
 * PackagesRepository.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.repository.packages

import com.uagr.kmp.course.domain.model.packages.PackageDataModel
import com.uagr.kmp.course.domain.model.packages.PackageModel
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface PackagesRepository {
    
    suspend fun getNetworkPackages(url : String) : Flow<NetworkResult<PackageModel>>
    
    suspend fun clearAndInsertPackages(packages: List<PackageDataModel>) : Flow<Unit>
    
    suspend fun getLocalPackages() : Flow<List<PackageDataModel>>
    
}