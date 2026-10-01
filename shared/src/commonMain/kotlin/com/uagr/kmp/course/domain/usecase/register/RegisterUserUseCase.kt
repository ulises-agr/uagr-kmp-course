/*
 * RegisterUserUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase.register

import com.uagr.kmp.course.domain.repository.register.RegisterRepository

class RegisterUserUseCase(
    private val registerRepository: RegisterRepository
) {
    val passwordRegex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#._-]).{10,}$")

    suspend operator fun invoke(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Result<Unit> {
        if (name.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            return Result.failure(IllegalArgumentException("Todos los campos son obligatorios"))
        }
        if (password != confirmPassword) {
            return Result.failure(IllegalArgumentException("Las contraseñas no coinciden"))
        }
        if (!password.matches(passwordRegex)) {
            return Result.failure(
                IllegalArgumentException("La contraseña debe tener al menos 10 caracteres, incluir mayúscula, minúscula, número y un símbolo.")
            )
        }
        return registerRepository.registerUser(
            name = name.trim(),
            email = email.trim(),
            password = password.trim()
        )
    }
}