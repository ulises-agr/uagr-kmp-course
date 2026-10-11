package com.uagr.kmp.course.domain.usecase.register

sealed class RegisterValidationResult {

    data object ValidationPassed : RegisterValidationResult()

    data class ValidationError(
        val nameError: RegisterValidationError? = null,
        val emailError: RegisterValidationError? = null,
        val passwordError: RegisterValidationError? = null,
        val confirmPasswordError: RegisterValidationError? = null
    ) : RegisterValidationResult()
}