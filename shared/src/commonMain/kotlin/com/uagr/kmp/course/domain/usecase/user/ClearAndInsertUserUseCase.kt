/*
 * ClearAndInsertUserUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase.user

import com.uagr.kmp.course.domain.model.user.UserModel
import com.uagr.kmp.course.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow

class ClearAndInsertUserUseCase(
    private val repository: UserRepository,
) {

    suspend fun clearAndInsertUser(
        email: String,
        password: String,
    ): Flow<Unit> = repository.clearAndInsertUser(
        user = UserModel(
            email = email,
            password = password,
        ),
    )
}
