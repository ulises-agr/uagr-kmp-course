package com.uagr.kmp.course.domain.usecase.budget

import org.koin.core.annotation.Factory

@Factory
class ValidateBudgetFormUseCase {
    operator fun invoke(): BudgetValidationResult = BudgetValidationResult.Success
}
