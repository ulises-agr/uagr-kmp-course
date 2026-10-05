/*
 * BudgetRepositoryImpl.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.repository.budget

import com.uagr.kmp.course.data.network.datasource.budget.BudgetNetworkDataSource
import com.uagr.kmp.course.data.network.model.request.budget.BudgetRequest
import com.uagr.kmp.course.domain.model.budget.BudgetModel
import com.uagr.kmp.course.domain.repository.budget.BudgetRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory

@Factory
class BudgetRepositoryImpl(
    private val networkDataSource: BudgetNetworkDataSource,
    private val dispatcher: CoroutineDispatcher,
) : BudgetRepository {

    override suspend fun fetchBudgetData(
        url: String,
        request: BudgetRequest,
    ): Flow<NetworkResult<BudgetModel>> = flow {
        emit(
            networkDataSource.fetchBudgetData(
                url = url,
                request = request,
            )
        )
    }.flowOn(context = dispatcher)
}
