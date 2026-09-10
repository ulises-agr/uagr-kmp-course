/*
 * ClearAndInsertPackagesUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase

import com.uagr.kmp.course.domain.model.PackagesModel
import com.uagr.kmp.course.domain.repository.PackagesRepository
import kotlinx.coroutines.flow.Flow

class ClearAndInsertPackagesUseCase(
    private val packagesRepository: PackagesRepository,
) {

    suspend operator fun invoke(packages: PackagesModel): Flow<Unit> =
        packagesRepository.clearAndInsertPackages(packages = packages)
}
