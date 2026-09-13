/*
 * ClearAndInsertPackagesUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase.packages

import com.uagr.kmp.course.domain.model.packages.PackagesModel
import com.uagr.kmp.course.domain.repository.packages.PackagesRepository
import kotlinx.coroutines.flow.Flow

class ClearAndInsertPackagesUseCase(
    private val packagesRepository: PackagesRepository,
) {

    suspend operator fun invoke(packages: PackagesModel): Flow<Unit> =
        packagesRepository.clearAndInsertPackages(packages = packages)
}
