package com.uagr.kmp.course.domain.usecase.packages

import com.uagr.kmp.course.domain.model.packages.PackageModel
import com.uagr.kmp.course.domain.repository.packages.PackagesRepository
import kotlinx.coroutines.flow.Flow

class GetLocalPackagesUseCase(
    private val packagesRepository: PackagesRepository
) {
    
    suspend operator fun invoke() : Flow<PackageModel?> =
        packagesRepository.getLocalPackages()
    
}
