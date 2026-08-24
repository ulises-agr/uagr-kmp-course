/*
 * UsersLocalDataSource.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.local.datasource.user

import com.uagr.kmp.course.domain.model.user.UserModel
import kotlinx.coroutines.flow.Flow

interface UsersLocalDataSource {
    // Data base
    suspend fun clearAndInsertUser(user: UserModel)
    suspend fun getFirstUser(): UserModel?
    // Data store
    suspend fun getUserToken(): Flow<String?>
    suspend fun saveUserToken(token: String)
}
