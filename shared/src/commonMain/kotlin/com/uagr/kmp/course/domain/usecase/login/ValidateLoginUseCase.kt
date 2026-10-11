package com.uagr.kmp.course.domain.usecase.login

class ValidateLoginUseCase {

    operator fun invoke(
        email: String,
        password: String
    ): LoginValidationResult {

        if (email.isBlank() && password.isBlank()) {
            return LoginValidationResult.EmptyEmailAndPassword
        }

        if (email.isBlank()) {
            return LoginValidationResult.EmptyEmail
        }

        if (password.isBlank()) {
            return LoginValidationResult.EmptyPassword
        }

        if (!isValidEmail(email)) {
            return LoginValidationResult.InvalidEmail
        }

        return LoginValidationResult.ValidationPassed
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        )

        return emailRegex.matches(email)
    }
}