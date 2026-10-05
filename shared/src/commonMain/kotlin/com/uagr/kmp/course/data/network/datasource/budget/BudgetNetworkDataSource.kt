/*
 * BudgetNetworkDataSource.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.network.datasource.budget

import com.uagr.kmp.course.data.network.model.request.budget.BudgetRequest
import com.uagr.kmp.course.domain.model.budget.BudgetModel
import com.uagr.kmp.course.utils.network.NetworkResult

interface BudgetNetworkDataSource {
    suspend fun fetchBudgetData(
        url: String,
        request: BudgetRequest,
    ): NetworkResult<BudgetModel>
}
