/*
 * GetLocalPackagesUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.domain.usecase

import com.uagr.packages.sdk.domain.model.PackagesDataModel
import com.uagr.packages.sdk.domain.repository.PackagesRepository
import com.uagr.packages.sdk.util.filterActivePackages
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetLocalPackagesUseCase(
    private val packagesRepository: PackagesRepository,
) {
    suspend operator fun invoke(): Flow<List<PackagesDataModel>> =
        packagesRepository.getLocalPackages()
            .map { packages -> filterActivePackages(packages = packages) }
}
