/*
 * loginUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase.login

import com.uagr.kmp.course.data.network.model.request.login.loginRequest
import com.uagr.kmp.course.domain.model.login.loginModel
import com.uagr.kmp.course.domain.repository.login.loginRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class loginUseCase(
    private val loginRepository: loginRepository,
) {

    suspend fun login(
        url: String,
        email: String,
        password: String,
    ): Flow<NetworkResult<loginModel>> =
        loginRepository.login(
            url = url,
            loginRequest = loginRequest(
                email = email,
                password = password,
                device_id = "3fa85f64-5717-4562-b3fc-2c963f66afa6",
            ),
        )
}
