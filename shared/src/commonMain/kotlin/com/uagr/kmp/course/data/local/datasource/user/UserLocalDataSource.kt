/*
 * UserLocalDataSource.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.local.datasource.user

import com.uagr.kmp.course.domain.model.login.UserModel

interface UserLocalDataSource {
    suspend fun insertUser(user : UserModel)
    suspend fun insertUserToken(token : String)
}