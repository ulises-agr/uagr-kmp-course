/*
// UserLocalDataSource.kt
// Created by Karla Judith Cervantes Higareda on 08/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.data.local.datasource.user

import com.uagr.kmp.course.domain.model.register.UserModel

interface UserLocalDataSource {
    suspend fun insertUserAndDelete(user: UserModel)
    suspend fun saveUserToken(token: String)
}
