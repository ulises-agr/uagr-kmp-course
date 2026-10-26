package com.uagr.kmp.course.data.repository.budget

import com.uagr.kmp.course.data.network.datasource.budget.BudgetNetworkDataSource
import com.uagr.kmp.course.domain.model.budget.BudgetListResultModel
import com.uagr.kmp.course.domain.model.budget.BudgetResumeModel
import com.uagr.kmp.course.domain.repository.budget.BudgetRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class BudgetReposirotyImpl(
    val budgetNetworkDataSource: BudgetNetworkDataSource,
    private val dispatcher: CoroutineDispatcher,
): BudgetRepository {
    override suspend fun getResumenPresupuesto(
        url: String,
        access_token: String,
        dateStart: String,
        dateEnd: String,
    ): Flow<NetworkResult<BudgetResumeModel>> = flow<NetworkResult<BudgetResumeModel>> {
        emit(budgetNetworkDataSource.getBudget(
            url = url,
            access_token = access_token,
            dateStart = dateStart,
            dateEnd = dateEnd
        ))
    }.flowOn(dispatcher)

    override suspend fun getListBudgets(
        url: String,
        access_token: String,
    ): Flow<NetworkResult<BudgetListResultModel>> = flow<NetworkResult<BudgetListResultModel>> {
        emit(budgetNetworkDataSource.getListBuget(
            url = url,
            access_token = access_token,
        ))
    }.flowOn(dispatcher)

}