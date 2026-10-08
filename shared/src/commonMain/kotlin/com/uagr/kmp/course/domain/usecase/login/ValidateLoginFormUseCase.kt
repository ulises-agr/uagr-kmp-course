/*
// ValidateLoginFormUseCase.kt
// Created by Karla Judith Cervantes Higareda on 08/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.domain.usecase.login

import org.koin.core.annotation.Factory

enum class LoginValidationError {
    REQUIRED_FIELDS,
    INVALID_EMAIL,
}

@Factory
class ValidateLoginFormUseCase {
    operator fun invoke(
        email: String,
        password: String,
    ): LoginValidationError? = when {
        email.isBlank() || password.isBlank() -> LoginValidationError.REQUIRED_FIELDS
        !Regex("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$").matches(email.trim()) ->
            LoginValidationError.INVALID_EMAIL
        else -> null
    }
}
