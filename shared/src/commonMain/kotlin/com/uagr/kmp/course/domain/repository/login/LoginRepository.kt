/*
 * LoginRepository.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.repository.login

import com.uagr.kmp.course.domain.model.login.LoginCredentialsModel
import com.uagr.kmp.course.domain.model.login.LoginDataModel
import com.uagr.kmp.course.util.network.api.NetworkResult
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(
        url: String,
        loginCredentials: LoginCredentialsModel,
    ): Flow<NetworkResult<LoginDataModel>>
}
