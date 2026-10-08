/*
// ValidateRegisterFormUseCase.kt
// Created by Karla Judith Cervantes Higareda on 01/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.domain.usecase.register

import org.koin.core.annotation.Factory

enum class RegisterValidationError {
    REQUIRED_FIELDS,
    INVALID_EMAIL,
    PASSWORD_MISMATCH,
}

@Factory
class ValidateRegisterFormUseCase {
    operator fun invoke(
        name: String,
        email: String,
        password: String,
        confirmPassword: String,
    ): RegisterValidationError? = when {
        name.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank() ->
            RegisterValidationError.REQUIRED_FIELDS
        !Regex("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$").matches(email.trim()) ->
            RegisterValidationError.INVALID_EMAIL
        password != confirmPassword -> RegisterValidationError.PASSWORD_MISMATCH
        else -> null
    }
}
