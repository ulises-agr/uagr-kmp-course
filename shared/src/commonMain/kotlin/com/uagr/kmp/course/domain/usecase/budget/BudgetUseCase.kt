package com.uagr.kmp.course.domain.usecase.budget

import com.uagr.kmp.course.data.network.model.request.budget.BudgetRequest
import com.uagr.kmp.course.domain.model.budget.BudgetModel
import com.uagr.kmp.course.domain.repository.budget.BudgetRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class BudgetUseCase(
    private val repository: BudgetRepository,
) {
    suspend fun fetchBudgetData(
        url: String,
        query: String = "",
    ): Flow<NetworkResult<BudgetModel>> =
        repository.fetchBudgetData(
            url = url,
            request = BudgetRequest(query = query),
        )
}
