package com.uagr.kmp.course.domain.usecase.register

class ValidateRegisterUseCase {

    operator fun invoke(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): RegisterValidationResult {

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
            return RegisterValidationResult.ValidationError(
                nameError = nameError,
                emailError = emailError,
                passwordError = passwordError,
                confirmPasswordError = confirmPasswordError
            )
        }

        return RegisterValidationResult.ValidationPassed
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        )

        return emailRegex.matches(email)
    }
}