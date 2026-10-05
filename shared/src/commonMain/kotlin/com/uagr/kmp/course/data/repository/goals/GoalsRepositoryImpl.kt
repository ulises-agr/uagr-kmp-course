/*
 * GoalsRepositoryImpl.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.repository.goals

import com.uagr.kmp.course.data.network.datasource.goals.GoalsNetworkDataSource
import com.uagr.kmp.course.data.network.model.request.goals.GoalsRequest
import com.uagr.kmp.course.domain.model.goals.GoalsModel
import com.uagr.kmp.course.domain.repository.goals.GoalsRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory

@Factory
class GoalsRepositoryImpl(
    private val networkDataSource: GoalsNetworkDataSource,
    private val dispatcher: CoroutineDispatcher,
) : GoalsRepository {

    override suspend fun fetchGoalsData(
        url: String,
        request: GoalsRequest,
    ): Flow<NetworkResult<GoalsModel>> = flow {
        emit(
            networkDataSource.fetchGoalsData(
                url = url,
                request = request,
            )
        )
    }.flowOn(context = dispatcher)
}
