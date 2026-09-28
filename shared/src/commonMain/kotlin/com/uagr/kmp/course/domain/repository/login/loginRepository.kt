/*
 * loginRepository.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.repository.login

import com.uagr.kmp.course.data.network.model.request.loginRequest
import com.uagr.kmp.course.domain.model.login.loginModel
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface loginRepository {
    suspend fun login(
        url: String,
        loginRequest: loginRequest,
    ): Flow<NetworkResult<loginModel>>
}
