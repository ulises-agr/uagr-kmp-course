/*
 * UserLocalDataSourceImpl.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.local.datasource.user

import com.uagr.kmp.course.data.local.database.dao.user.UserDao
import com.uagr.kmp.course.data.local.secure.SecureTokenStorage
import com.uagr.kmp.course.domain.mapper.user.toEntity
import com.uagr.kmp.course.domain.model.user.userModel
import org.koin.core.annotation.Factory

@Factory
class UserLocalDataSourceImpl(
    private val userDao: UserDao,
    private val secureTokenStorage: SecureTokenStorage,
): UserLocalDataSource {

    override suspend fun insertUserAndDelete(user: userModel) {
        userDao.insertUserAndDeleteOld(user = user.toEntity())
    }

    override suspend fun saveUserToken(token: String) {
        secureTokenStorage.saveToken(token = token)
    }
}
