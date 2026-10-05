/*
 * RegisterUserUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase.register

import com.uagr.kmp.course.data.network.model.request.register.RegisterRequest
import com.uagr.kmp.course.domain.model.register.RegisterModel
import com.uagr.kmp.course.domain.repository.register.RegisterRepository
import com.uagr.kmp.course.utils.constant.NetworkUrl
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.annotation.Factory

@Factory
class RegisterUserUseCase(
    private val registerRepository: RegisterRepository
) {
    private val passwordRegex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#._-]).{10,}$")

    suspend operator fun invoke(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Flow<NetworkResult<RegisterModel>> {
        val trimmedName = name.trim()
        val trimmedEmail = email.trim()
        val trimmedPassword = password.trim()
        val trimmedConfirmPassword = confirmPassword.trim()

        if (trimmedName.isBlank() || trimmedEmail.isBlank() || trimmedPassword.isBlank() || trimmedConfirmPassword.isBlank()) {
            return flowOf(NetworkResult.Error(message = "Todos los campos son obligatorios"))
        }
        if (trimmedPassword != trimmedConfirmPassword) {
            return flowOf(NetworkResult.Error(message = "Las contraseñas no coinciden"))
        }
        if (!trimmedPassword.matches(passwordRegex)) {
            return flowOf(
                NetworkResult.Error(
                    message = "La contraseña debe tener al menos 10 caracteres, incluir mayúscula, minúscula, número y un símbolo."
                )
            )
        }

        return registerRepository.registerUser(
            url = NetworkUrl.REGISTER_ENDPOINT,
            registerRequest = RegisterRequest(
                name = trimmedName,
                email = trimmedEmail,
                password = trimmedPassword
            )
        )
    }
}
