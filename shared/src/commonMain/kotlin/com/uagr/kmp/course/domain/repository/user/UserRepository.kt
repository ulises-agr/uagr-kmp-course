/*
// UserRepository.kt
// Created by Karla Judith Cervantes Higareda on 08/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.domain.repository.user

import com.uagr.kmp.course.domain.model.register.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUserAndDelete(user: UserModel): Flow<Unit>
    suspend fun saveUserToken(token: String): Flow<Unit>
}
