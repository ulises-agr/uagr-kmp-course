package com.uagr.kmp.course.domain.usecase.register

import com.uagr.kmp.course.data.network.model.request.register.RegisterRequest
import com.uagr.kmp.course.domain.model.register.RegisterModel
import com.uagr.kmp.course.domain.repository.repository.RegisterRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

class RegisterUseCase(
    private val registerRepository: RegisterRepository,
){
    suspend operator fun invoke(
        url: String,
        name: String,
        email: String,
        password: String,
    ): Flow<NetworkResult<RegisterModel>> =
        registerRepository.register(
            url = url,
            registerRequest = RegisterRequest(
                name = name,
                email = email,
                password = password
            )
        )
}
