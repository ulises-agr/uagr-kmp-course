/*
 * GetUserTokenUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase.user

import com.uagr.kmp.course.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow

class SaveUserTokenUseCase(
    private val repository: UserRepository,
) {

    suspend fun saveUserToken(token: String): Flow<Unit> =
        repository.saveUserToken(token = token)
}
