package com.uagr.kmp.course.data.repository.packages

import com.uagr.kmp.course.data.local.datasource.packages.PackagesLocalDataSource
import com.uagr.kmp.course.data.network.datasource.packages.PackagesNetworkDataSource
import com.uagr.kmp.course.domain.model.packages.PackageDataModel
import com.uagr.kmp.course.domain.model.packages.PackageModel
import com.uagr.kmp.course.domain.repository.packages.PackagesRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory

@Factory
class PackageRepositoryImpl(
    private val packagesNetworkDataSource: PackagesNetworkDataSource,
    private val packagesLocalDataSource: PackagesLocalDataSource,
    private val dispatcher: CoroutineDispatcher
) : PackagesRepository {
    
    override suspend fun getNetworkPackages(url: String): Flow<NetworkResult<PackageModel>> = flow {
        emit(value = packagesNetworkDataSource.getPackages(url = url))
    }.flowOn(context = dispatcher)
    
    override suspend fun clearAndInsertPackages(packages: List<PackageDataModel>): Flow<Unit> = flow {
        emit(value = packagesLocalDataSource.clearAndInsertPackages(packages = packages))
    }.flowOn(context = dispatcher)
    
    override suspend fun getLocalPackages(): Flow<List<PackageDataModel>> = flow {
        emit(value = packagesLocalDataSource.getPackages())
    }.flowOn(context = dispatcher)
}