package com.uagr.kmp.course.domain.mapper.budget

import com.uagr.kmp.course.data.network.model.response.budget.BudgetListResponse
import com.uagr.kmp.course.data.network.model.response.budget.BudgetListResult
import com.uagr.kmp.course.data.network.model.response.budget.BudgetsResponse
import com.uagr.kmp.course.domain.model.budget.BudgetListModel
import com.uagr.kmp.course.domain.model.budget.BudgetListResultModel
import com.uagr.kmp.course.domain.model.budget.BudgetResumeModel


fun BudgetsResponse.toDomain(): BudgetResumeModel =
    BudgetResumeModel(
        budget = budget.orEmpty(),
        spent = spent.orEmpty(),
        remaining = remaining.orEmpty(),
        percentage =percentage.orEmpty(),
    )
fun BudgetListResponse.toDomain(): BudgetListResultModel =
    BudgetListResultModel(
        data = data.orEmpty().map { it.toDomain() }
    )
fun BudgetListResult.toDomain(): BudgetListModel =
    BudgetListModel(
        id = id.orEmpty(),
        category_id = category_id.orEmpty(),
        amount = amount.orEmpty(),
        period = period.orEmpty(),
        start_date = start_date.orEmpty(),
        end_date = end_date.orEmpty(),
        created_at = created_at.orEmpty(),
        updated_at = updated_at.orEmpty(),
        version = version ?: 0
    )