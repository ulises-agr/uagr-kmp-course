/*
 * GetPackagesUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase

import com.uagr.kmp.course.domain.model.PackagesModel
import com.uagr.kmp.course.domain.repository.PackagesRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetPackagesUseCase(
    private val packagesRepository: PackagesRepository,
) {

    suspend operator fun invoke(url: String): Flow<NetworkResult<PackagesModel>> =
        packagesRepository.getPackages(url = url)
}
