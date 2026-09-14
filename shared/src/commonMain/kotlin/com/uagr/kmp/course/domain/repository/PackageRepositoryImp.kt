package com.uagr.kmp.course.domain.repository

import com.uagr.kmp.course.data.local.datasource.PackagesLocalDataSourceImp
import com.uagr.kmp.course.data.network.datasource.PackagesNetworkDataSource
import com.uagr.kmp.course.domain.model.PackageModel
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PackageRepositoryImp(
    private val packagesNetworkDataSource: PackagesNetworkDataSource,
    private val packagesLocalDataSourceImp: PackagesLocalDataSourceImp,
    private val dispatcher: CoroutineDispatcher
) : PackagesRepository {
    
    override suspend fun getNetworkPackages(url: String): Flow<NetworkResult<PackageModel>> = flow {
        emit( value = packagesNetworkDataSource.getPackages(url = url))
    }.flowOn(context = dispatcher)
    
    override suspend fun clearAndInsertPackages(packageModel: PackageModel): Flow<Unit> = flow {
        emit( value = packagesLocalDataSourceImp.clearAndInsertPackages(packageModel = packageModel))
    }.flowOn(context = dispatcher)
    
    override suspend fun getLocalPackages(): Flow<PackageModel?> = flow {
        emit( value = packagesLocalDataSourceImp.getPackages())
    }.flowOn(context = dispatcher)
}