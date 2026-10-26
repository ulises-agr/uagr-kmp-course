package com.uagr.kmp.course.domain.usecase.goals

import com.uagr.kmp.course.domain.model.budget.BudgetResumeModel
import com.uagr.kmp.course.domain.repository.budget.BudgetRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetBudgetResumeUseCase(
    private val budgetRepository: BudgetRepository,
    ) {
    suspend operator fun invoke(
        url_Budget: String,
        access_Token: String,
    ): Flow<NetworkResult<BudgetResumeModel>>  =
        budgetRepository.getResumenPresupuesto(
            url = url_Budget,
            access_token = access_Token,
            dateStart = "2026-10-01",
            dateEnd = "2026-10-31"
        )
}