/*
 * GetNetworkPackagesUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.domain.usecase

import com.uagr.packages.sdk.domain.model.PackagesModel
import com.uagr.packages.sdk.domain.repository.PackagesRepository
import com.uagr.packages.sdk.internal.network.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetNetworkPackagesUseCase(
    private val packagesRepository: PackagesRepository,
) {
    suspend operator fun invoke(url: String): Flow<NetworkResult<PackagesModel>> =
        packagesRepository.getNetworkPackages(url = url)
}
