/*
 * GetUserTokenUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase.user

import com.uagr.kmp.course.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUserTokenUseCase(
    private val repository: UserRepository,
) {

    suspend fun getUserToken(): Flow<String?> =
        repository.getUserToken()
}
