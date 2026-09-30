/*
 * RegisterUserUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase.register

import com.uagr.kmp.course.domain.repository.register.RegisterRepository

class RegisterUserUseCase(
    private val registerRepository: RegisterRepository
) {
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
        return registerRepository.registerUser(
            name = name.trim(),
            email = email.trim(),
            password = password
        )
    }
}