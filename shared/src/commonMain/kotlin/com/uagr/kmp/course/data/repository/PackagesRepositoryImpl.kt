/*
 * PackagesRepositoryImpl.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.repository

import com.uagr.kmp.course.data.network.datasource.PackagesNetworkDataSource
import com.uagr.kmp.course.domain.model.PackagesModel
import com.uagr.kmp.course.domain.repository.PackagesRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PackagesRepositoryImpl(
    private val packagesNetworkDataSource: PackagesNetworkDataSource,
    private val dispatcher: CoroutineDispatcher,
): PackagesRepository {

    override suspend fun getPackages(url: String): Flow<NetworkResult<PackagesModel>> = flow {
        emit(value = packagesNetworkDataSource.getPackages(url = url))
    }.flowOn(context = dispatcher)
}
