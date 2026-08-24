/*
 * UsersLocalDataSourceImpl.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.local.datasource.user

import com.uagr.kmp.course.data.local.database.dao.users.UsersDao
import com.uagr.kmp.course.data.local.datastore.AppDataStore
import com.uagr.kmp.course.domain.mappers.user.toDomain
import com.uagr.kmp.course.domain.mappers.user.toEntity
import com.uagr.kmp.course.domain.model.user.UserModel
import kotlinx.coroutines.flow.Flow

class UsersLocalDataSourceImpl(
    private val usersDao: UsersDao,
    private val dataStore: AppDataStore,
): UsersLocalDataSource {

    // Data base
    override suspend fun clearAndInsertUser(user: UserModel) =
        usersDao.clearAndInsertUser(user = user.toEntity())

    override suspend fun getFirstUser(): UserModel? =
        usersDao.getFirstUser()?.toDomain()

    // Data store
    override suspend fun getUserToken(): Flow<String?> =
        dataStore.userToken

    override suspend fun saveUserToken(token: String) =
        dataStore.saveToken(token = token)
}
