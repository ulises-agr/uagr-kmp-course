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
            "Ingresa tu nombre"
        } else {
            null
        }

        val emailError = when {
            email.isBlank() -> "Ingresa tu correo"
            !isValidEmail(email) -> "Ingresa un correo valido"
            else -> null
        }

        val passwordError = if (password.isBlank()) {
            "Ingresa tu contraseña"
        } else {
            null
        }

        val confirmPasswordError = when {
            confirmPassword.isBlank() -> "Confirma tu contraseña"
            password != confirmPassword -> "Las contraseñas no coinciden"
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