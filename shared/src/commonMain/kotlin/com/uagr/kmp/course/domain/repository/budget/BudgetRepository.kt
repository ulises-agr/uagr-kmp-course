package com.uagr.kmp.course.domain.repository.budget

import com.uagr.kmp.course.domain.model.budget.BudgetListResultModel
import com.uagr.kmp.course.domain.model.budget.BudgetResumeModel
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface BudgetRepository{
    suspend fun getResumenPresupuesto(
        url: String,
        access_token: String,
        dateStart: String,
        dateEnd: String,
    ): Flow<NetworkResult<BudgetResumeModel>>

    suspend fun getListBudgets(
        url: String,
        access_token: String,
    ): Flow<NetworkResult<BudgetListResultModel>>
}