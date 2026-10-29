/*
 * ClearAndInsertPackagesUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.domain.usecase

import com.uagr.packages.sdk.domain.model.PackagesDataModel
import com.uagr.packages.sdk.domain.repository.PackagesRepository
import kotlinx.coroutines.flow.Flow

class ClearAndInsertPackagesUseCase(
    private val packagesRepository: PackagesRepository,
) {
    suspend operator fun invoke(packages: List<PackagesDataModel>): Flow<Unit> =
        packagesRepository.clearAndInsertPackages(packages = packages)
}
