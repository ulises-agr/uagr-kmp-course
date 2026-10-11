package com.uagr.kmp.course.domain.usecase.login

import com.uagr.kmp.course.domain.repository.login.LoginRepository

class LoginUseCase(
    private val loginRepository: LoginRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ): LoginResult {

        val success = loginRepository.login(
            email = email,
            password = password
        )

        return if (success) {
            LoginResult.Success
        } else {
            LoginResult.InvalidCredentials
        }
    }
}