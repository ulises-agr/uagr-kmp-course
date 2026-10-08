package com.uagr.kmp.course.domain.usecase.register

import RegisterResult
import com.uagr.kmp.course.domain.repository.register.RegisterRepository

class RegisterUseCase(
    private val registerRepository: RegisterRepository
) {

    suspend operator fun invoke(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): RegisterResult {

        val nameError = if (name.isBlank()) {
            RegisterValidationError.NAME_EMPTY
        } else {
            null
        }

        val emailError = when {
            email.isBlank() -> RegisterValidationError.EMAIL_EMPTY
            !isValidEmail(email) -> RegisterValidationError.EMAIL_INVALID
            else -> null
        }

        val passwordError = if (password.isBlank()) {
            RegisterValidationError.PASSWORD_EMPTY
        } else {
            null
        }

        val confirmPasswordError = when {
            confirmPassword.isBlank() ->
                RegisterValidationError.CONFIRM_PASSWORD_EMPTY

            password != confirmPassword ->
                RegisterValidationError.PASSWORD_MISMATCH

            else -> null
        }

        if (
            nameError != null ||
            emailError != null ||
            passwordError != null ||
            confirmPasswordError != null
        ) {
            return RegisterResult.ValidationError(
                nameError = nameError,
                emailError = emailError,
                passwordError = passwordError,
                confirmPasswordError = confirmPasswordError
            )
        }

        val success = registerRepository.register(
            name = name,
            email = email,
            password = password
        )

        return if (success) {
            RegisterResult.Success
        } else {
            RegisterResult.RegisterFailed
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".")
    }
}