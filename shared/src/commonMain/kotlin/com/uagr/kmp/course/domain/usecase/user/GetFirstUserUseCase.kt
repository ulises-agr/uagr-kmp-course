/*
 * GetFirstUserUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase.user

import com.uagr.kmp.course.domain.model.user.UserModel
import com.uagr.kmp.course.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow

class GetFirstUserUseCase(
    private val repository: UserRepository,
) {

    suspend fun getFirstUser(): Flow<UserModel?> =
        repository.getFirstUser()
}
