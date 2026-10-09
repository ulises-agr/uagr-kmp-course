/*
 * UserLocalDataSourceImpl.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.local.datasource.user

import com.uagr.kmp.course.data.local.database.dao.user.UserDao
import com.uagr.kmp.course.data.local.datastore.AppDataStore
import com.uagr.kmp.course.data.local.model.user.UserEntity
import com.uagr.kmp.course.domain.mapper.user.toDomain
import com.uagr.kmp.course.domain.mapper.user.toEntity
import com.uagr.kmp.course.domain.model.user.UserModel
import org.koin.core.annotation.Factory

@Factory
class UserLocalDataSourceImpl(
    private val userDao: UserDao,
    private val appDataStore: AppDataStore,
): UserLocalDataSource {

    override suspend fun insertUserAndDelete(user: UserModel) {
        userDao.insertUserAndDeleteOld(user = user.toEntity())
    }

    override suspend fun saveUserToken(token: String) {
        appDataStore.saveUserToken(token = token)
    }

    override suspend fun getUser(): UserModel =
        userDao.getUser().toDomain()

    override suspend fun getUserToken(): String =
        appDataStore.getUserToken()
}
