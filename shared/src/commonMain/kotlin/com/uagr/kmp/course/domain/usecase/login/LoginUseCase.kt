/*
// LoginUseCase.kt
// Created by Karla Judith Cervantes Higareda on 01/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.domain.usecase.login

import com.uagr.kmp.course.data.network.model.request.LoginRequest
import com.uagr.kmp.course.domain.model.login.LoginModel
import com.uagr.kmp.course.domain.repository.login.LoginRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class LoginUseCase(
    private val loginRepository: LoginRepository,
) {
    suspend fun login(
        url: String,
        email: String,
        password: String,
        deviceId: String,
    ): Flow<NetworkResult<LoginModel>> =
        loginRepository.login(
            url = url,
            request = LoginRequest(
                email = email,
                password = password,
                deviceId = deviceId,
            ),
        )

}
