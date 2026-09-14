package com.uagr.kmp.course.domain.usecase

import com.uagr.kmp.course.domain.model.PackageModel
import com.uagr.kmp.course.domain.repository.PackagesRepository
import kotlinx.coroutines.flow.Flow

class ClearAndInsertPackagesUseCase(
    private val packagesRepository: PackagesRepository
) {
    
    suspend operator fun invoke(packageModel: PackageModel) : Flow<Unit> =
        packagesRepository.clearAndInsertPackages(packageModel = packageModel)
    
}