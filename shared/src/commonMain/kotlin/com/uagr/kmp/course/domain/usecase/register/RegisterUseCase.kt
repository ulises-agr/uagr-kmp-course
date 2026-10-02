package com.uagr.kmp.course.domain.usecase.register

import com.uagr.kmp.course.data.network.model.request.register.RegisterRequest
import com.uagr.kmp.course.domain.model.register.RegisterModel
import com.uagr.kmp.course.domain.repository.register.RegisterRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class RegisterUseCase(
    private val registerRepository: RegisterRepository,
) {

    suspend fun register(
        url: String,
        email: String,
        password: String,
        name: String,
    ): Flow<NetworkResult<RegisterModel>> =
        registerRepository.register(
            url = url,
            registerRequest = RegisterRequest(
                email = email,
                password = password,
                name = name,
            ),
        )
}
