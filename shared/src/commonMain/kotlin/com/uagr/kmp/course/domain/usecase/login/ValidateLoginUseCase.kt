/*
 * ValidateLoginUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase.login

class ValidateLoginUseCase {

    operator fun invoke(
        email: String,
        password: String,
    ): ValidationResult =
        when {
            email.isEmpty() -> ValidationResult.EmailEmpty
            password.isEmpty() -> ValidationResult.PasswordEmpty
            else -> ValidationResult.ValidationSuccess
        }
}
