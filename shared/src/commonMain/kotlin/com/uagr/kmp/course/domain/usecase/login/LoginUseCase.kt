package com.uagr.kmp.course.domain.usecase.login

import com.uagr.kmp.course.domain.repository.login.LoginRepository

class LoginUseCase(
    private val loginRepository: LoginRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ): Boolean {
        return loginRepository.login(
            email = email,
            password = password
        )
    }
}