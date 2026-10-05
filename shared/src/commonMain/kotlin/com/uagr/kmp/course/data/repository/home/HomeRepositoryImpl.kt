/*
 * HomeRepositoryImpl.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.repository.home

import com.uagr.kmp.course.data.network.datasource.home.HomeNetworkDataSource
import com.uagr.kmp.course.data.network.model.request.home.HomeRequest
import com.uagr.kmp.course.domain.model.home.HomeModel
import com.uagr.kmp.course.domain.repository.home.HomeRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory

@Factory
class HomeRepositoryImpl(
    private val networkDataSource: HomeNetworkDataSource,
    private val dispatcher: CoroutineDispatcher,
) : HomeRepository {

    override suspend fun fetchHomeData(
        url: String,
        request: HomeRequest,
    ): Flow<NetworkResult<HomeModel>> = flow {
        emit(
            networkDataSource.fetchHomeData(
                url = url,
                request = request,
            )
        )
    }.flowOn(context = dispatcher)
}
