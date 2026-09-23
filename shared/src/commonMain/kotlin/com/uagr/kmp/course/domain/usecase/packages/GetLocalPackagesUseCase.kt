/*
 * GetLocalPackagesUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase.packages

import com.uagr.kmp.course.domain.model.packages.PackagesDataModel
import com.uagr.kmp.course.domain.repository.packages.PackagesRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class GetLocalPackagesUseCase(
    private val packagesRepository: PackagesRepository,
) {

    suspend operator fun invoke(): Flow<PackagesDataModel?> =
        packagesRepository.getLocalPackages()
}
