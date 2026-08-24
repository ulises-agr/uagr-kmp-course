/*
 * LoginRepository.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.repository.user

import com.uagr.kmp.course.domain.model.user.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    // Data base
    suspend fun clearAndInsertUser(user: UserModel): Flow<Unit>
    suspend fun getFirstUser(): Flow<UserModel?>
    // Data store
    suspend fun getUserToken(): Flow<String?>
    suspend fun saveUserToken(token: String): Flow<Unit>
}
