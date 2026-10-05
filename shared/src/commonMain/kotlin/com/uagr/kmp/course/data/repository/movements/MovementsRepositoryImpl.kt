/*
 * MovementsRepositoryImpl.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.repository.movements

import com.uagr.kmp.course.data.network.datasource.movements.MovementsNetworkDataSource
import com.uagr.kmp.course.data.network.model.request.movements.MovementsRequest
import com.uagr.kmp.course.domain.model.movements.MovementsModel
import com.uagr.kmp.course.domain.repository.movements.MovementsRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory

@Factory
class MovementsRepositoryImpl(
    private val networkDataSource: MovementsNetworkDataSource,
    private val dispatcher: CoroutineDispatcher,
) : MovementsRepository {

    override suspend fun fetchMovementsData(
        url: String,
        request: MovementsRequest,
    ): Flow<NetworkResult<MovementsModel>> = flow {
        emit(
            networkDataSource.fetchMovementsData(
                url = url,
                request = request,
            )
        )
    }.flowOn(context = dispatcher)
}
