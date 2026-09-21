/*
 * GetNetworkPackagesUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase.packages

import com.uagr.kmp.course.domain.model.packages.PackageModel
import com.uagr.kmp.course.domain.repository.packages.PackagesRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetNetworkPackagesUseCase(
    private val packagesRepository: PackagesRepository
) {
    suspend operator fun invoke(url: String): Flow<NetworkResult<PackageModel>> =
        packagesRepository.getNetworkPackages(url = url)
}


