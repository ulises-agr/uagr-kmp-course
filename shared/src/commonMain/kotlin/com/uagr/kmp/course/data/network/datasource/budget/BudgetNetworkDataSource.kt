package com.uagr.kmp.course.data.network.datasource.budget

import com.uagr.kmp.course.data.network.model.response.budget.BudgetListResponse
import com.uagr.kmp.course.data.network.model.response.budget.BudgetsResponse
import com.uagr.kmp.course.domain.model.budget.BudgetListResultModel
import com.uagr.kmp.course.domain.model.budget.BudgetResumeModel
import com.uagr.kmp.course.utils.network.NetworkResult

interface BudgetNetworkDataSource {
    suspend fun getBudget(
        url: String,
        access_token: String,
    ): NetworkResult<BudgetResumeModel>

    suspend fun getListBuget(
        url: String,
        access_token: String,
        dateStart: String,
        dateEnd: String
    ): NetworkResult<BudgetListResultModel>
}