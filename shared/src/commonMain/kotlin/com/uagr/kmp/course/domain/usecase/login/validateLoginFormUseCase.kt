/*
 * validateLoginFormUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase.login

import org.koin.core.annotation.Factory

@Factory
class validateLoginFormUseCase {

    operator fun invoke(
        email: String,
        password: String,
    ): loginValidationResult =
        when {
            email.isBlank() -> loginValidationResult.EmailEmpty
            !email.isValidEmail() -> loginValidationResult.EmailInvalid
            password.isBlank() -> loginValidationResult.PasswordEmpty
            else -> loginValidationResult.Success
        }

    private fun String.isValidEmail(): Boolean {
        val trimmed = trim()
        val atIndex = trimmed.indexOf('@')
        if (atIndex <= 0) return false
        val domain = trimmed.substring(startIndex = atIndex + 1)
        return domain.contains('.') && domain.last() != '.'
    }
}
