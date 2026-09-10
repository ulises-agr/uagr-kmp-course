/*
 * GetLocalPackagesUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase

import com.uagr.kmp.course.domain.model.PackagesModel
import com.uagr.kmp.course.domain.repository.PackagesRepository
import kotlinx.coroutines.flow.Flow

class GetLocalPackagesUseCase(
    private val packagesRepository: PackagesRepository,
) {

    suspend operator fun invoke(): Flow<PackagesModel?> =
        packagesRepository.getLocalPackages()
}
