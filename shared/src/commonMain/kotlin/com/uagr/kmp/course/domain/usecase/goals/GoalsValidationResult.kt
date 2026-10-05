package com.uagr.kmp.course.domain.usecase.goals

sealed class GoalsValidationResult {
    data object Success : GoalsValidationResult()
}
