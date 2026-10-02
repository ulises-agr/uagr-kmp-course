package com.uagr.kmp.course.domain.usecase.register

import org.koin.core.annotation.Factory

@Factory
class ValidateRegisterFormUseCase {

    operator fun invoke(
        email: String,
        password: String,
        name: String,
        confirmPassword: String,
    ): RegisterValidationResult =
        when {
            name.isBlank() -> RegisterValidationResult.NameEmpty
            email.isBlank() -> RegisterValidationResult.EmailEmpty
            password.isBlank() -> RegisterValidationResult.PasswordEmpty
            confirmPassword.isBlank() -> RegisterValidationResult.ConfirmPasswordEmpty
            password.equals(confirmPassword) -> RegisterValidationResult.DifferentPasswords
            else -> RegisterValidationResult.Success
        }
}
