package com.uagr.kmp.course.domain.usecase.movements

sealed class MovementsValidationResult {
    data object Success : MovementsValidationResult()
}
