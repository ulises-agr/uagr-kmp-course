package com.uagr.kmp.course.domain.usecase.home

sealed class HomeValidationResult {
    data object Success : HomeValidationResult()
}
