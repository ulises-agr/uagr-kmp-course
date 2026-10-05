package com.uagr.kmp.course.domain.usecase.goals

import org.koin.core.annotation.Factory

@Factory
class ValidateGoalsFormUseCase {
    operator fun invoke(): GoalsValidationResult = GoalsValidationResult.Success
}
