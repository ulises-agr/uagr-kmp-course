package com.uagr.kmp.course.domain.usecase.budget

sealed class BudgetValidationResult {
    data object Success : BudgetValidationResult()
}
