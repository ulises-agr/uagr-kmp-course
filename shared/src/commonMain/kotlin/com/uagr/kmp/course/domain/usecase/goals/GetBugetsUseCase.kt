package com.uagr.kmp.course.domain.usecase.goals

import com.uagr.kmp.course.domain.model.budget.BudgetWithCategorytModel
//import com.uagr.kmp.course.domain.usecase.transactions.GetCategoriesUseCase
import kotlinx.coroutines.flow.combine
import com.uagr.kmp.course.domain.repository.budget.BudgetRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlin.String

/*class GetBugetsUseCase(
    val budgetRepository: BudgetRepository,
    val categoriesUseCase: GetCategoriesUseCase,
) {
    suspend operator fun invoke(url_Budget: String,access_Token: String, url_Category: String): Flow<NetworkResult<List<BudgetWithCategorytModel>>> = combine(
        budgetRepository.getListBudgets(
            url = url_Budget,
            access_token = access_Token,
            dateStart = "2026-10-01",
            dateEnd = "2026-10-31",
        ),
        categoriesUseCase.invoke(
            url = url_Category,
            acces_token = access_Token
        )
    ){ budgetResult, categoryResult ->
        if (budgetResult is NetworkResult.Success && categoryResult is NetworkResult.Success){
            val budgets = budgetResult.response
            val categories = categoryResult.response
            val categoryById = categories.data.associateBy { it.id }
            val budgetsWithCategory = budgets.data.map{ budget ->
                val category = categoryById[budget.category_id]
                BudgetWithCategorytModel(
                    id = budget.id,
                    category_name = category?.name ?: "No esta esa categoria",
                    amount = budget.amount,
                    period = budget.period,
                    start_date = budget.start_date,
                    end_date = budget.end_date,
                )
            }
            NetworkResult.Success(budgetsWithCategory)
        }
        NetworkResult.Error("Error de algun tipo")
    }
}*/