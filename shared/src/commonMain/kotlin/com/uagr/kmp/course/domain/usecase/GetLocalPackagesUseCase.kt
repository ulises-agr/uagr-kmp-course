package com.uagr.kmp.course.domain.usecase

import com.uagr.kmp.course.domain.model.PackageModel
import com.uagr.kmp.course.domain.repository.PackagesRepository
import kotlinx.coroutines.flow.Flow

class GetLocalPackagesUseCase(
    private val packagesRepository: PackagesRepository
) {
    
    suspend operator fun invoke() : Flow<PackageModel?> =
        packagesRepository.getLocalPackages()
    
}
