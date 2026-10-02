/*
 * RegisterRepository.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.domain.repository.register

import com.uagr.kmp.course.data.network.model.request.register.RegisterRequest
import com.uagr.kmp.course.domain.model.register.RegisterModel
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    suspend fun register(
        url: String,
        registerRequest: RegisterRequest,
    ): Flow<NetworkResult<RegisterModel>>
}
