/*
 * ValidateLoginFormUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase.login

class ValidateLoginFormUseCase {

    operator fun invoke(
        email: String,
        password: String,
    ): LoginValidationResult =
        when {
            email.isBlank() -> LoginValidationResult.EmailEmpty
            password.isBlank() -> LoginValidationResult.PasswordEmpty
            else -> LoginValidationResult.Success
        }
}
