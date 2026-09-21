package com.uagr.kmp.course.domain.usecase.packages

import com.uagr.kmp.course.domain.model.packages.PackageModel
import com.uagr.kmp.course.domain.repository.packages.PackagesRepository
import kotlinx.coroutines.flow.Flow

class ClearAndInsertPackagesUseCase(
    private val packagesRepository: PackagesRepository
) {
    
    suspend operator fun invoke(packageModel: PackageModel) : Flow<Unit> =
        packagesRepository.clearAndInsertPackages(packageModel = packageModel)
    
}