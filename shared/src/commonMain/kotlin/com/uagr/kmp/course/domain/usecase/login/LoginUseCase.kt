/*
 * LoginUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase.login

import com.uagr.kmp.course.utils.network.api.NetworkResult
import com.uagr.kmp.course.domain.model.login.LoginCredentialsModel
import com.uagr.kmp.course.domain.model.login.LoginDataModel
import com.uagr.kmp.course.domain.repository.login.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(
    private val repository: LoginRepository,
) {

    suspend fun login(
        url: String,
        email: String,
        password: String,
    ): Flow<NetworkResult<LoginDataModel>> = repository.login(
        url = url,
        loginCredentials = LoginCredentialsModel(
            email = email.trim(),
            password = password.trim(),
        )
    )
}
