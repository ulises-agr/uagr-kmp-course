/*
 * BudgetRepository.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.domain.repository.budget

import com.uagr.kmp.course.data.network.model.request.budget.BudgetRequest
import com.uagr.kmp.course.domain.model.budget.BudgetModel
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface BudgetRepository {
    suspend fun fetchBudgetData(
        url: String,
        request: BudgetRequest,
    ): Flow<NetworkResult<BudgetModel>>
}
