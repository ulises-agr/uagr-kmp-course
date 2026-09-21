/*
 * LoginUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase.login

import com.uagr.kmp.course.data.network.model.login.request.LoginRequest
import com.uagr.kmp.course.domain.model.login.LoginModel
import com.uagr.kmp.course.domain.repository.login.LoginRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

class LoginUseCase(
    private val loginRepository: LoginRepository
) {
    
    suspend fun login(
        url : String,
        email : String,
        password : String
    ) : Flow<NetworkResult<LoginModel>> =
        loginRepository.login(
            url = url,
            loginRequest = LoginRequest(
                password = password,
                email = email
            )
        )
}
