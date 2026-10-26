package com.uagr.kmp.course.domain.usecase.goals

import com.uagr.kmp.course.domain.model.budget.BudgetListResultModel
import com.uagr.kmp.course.domain.repository.budget.BudgetRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlin.String

class GetBugetsUseCase(
    val budgetRepository: BudgetRepository,
) {
    suspend operator fun invoke(
        url_Budget: String,
        access_Token: String,
    ): Flow<NetworkResult<BudgetListResultModel>>  =
        budgetRepository.getListBudgets(
            url = url_Budget,
            access_token = access_Token,

        )
}