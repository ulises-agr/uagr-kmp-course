/*
 * ValidateLoginUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase.login

import org.koin.core.annotation.Factory

@Factory
class ValidateLoginUseCase {

    operator fun invoke(
        email: String,
        password: String,
    ): LoginValidationResult =
        when {
            email.isBlank() -> LoginValidationResult.EmailEmpty
            !email.isValidEmail() -> LoginValidationResult.EmailInvalid
            password.isBlank() -> LoginValidationResult.PasswordEmpty
            else -> LoginValidationResult.Success
        }

    private fun String.isValidEmail(): Boolean {
        val trimmed = trim()
        val atIndex = trimmed.indexOf('@')
        if (atIndex <= 0) return false
        val domain = trimmed.substring(startIndex = atIndex + 1)
        return domain.contains('.') && domain.last() != '.'
    }
}
