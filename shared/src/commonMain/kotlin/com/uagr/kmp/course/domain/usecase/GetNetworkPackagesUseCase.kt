/*
 * GetNetworkPackagesUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase

import com.uagr.kmp.course.domain.model.PackageModel
import com.uagr.kmp.course.domain.repository.PackagesRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetNetworkPackagesUseCase(
    private val packagesRepository: PackagesRepository
) {
    suspend operator fun invoke(url: String): Flow<NetworkResult<PackageModel>> =
        packagesRepository.getNetworkPackages(url = url)
}


