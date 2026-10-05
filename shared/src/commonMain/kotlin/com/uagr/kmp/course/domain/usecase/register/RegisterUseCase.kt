package com.uagr.kmp.course.domain.usecase.register

import com.uagr.kmp.course.domain.repository.register.RegisterRepository

class RegisterUseCase(
    private val registerRepository: RegisterRepository
) {

    suspend operator fun invoke(
        name: String,
        email: String,
        password: String
    ): Boolean {
        return registerRepository.register(
            name = name,
            email = email,
            password = password
        )
    }
}