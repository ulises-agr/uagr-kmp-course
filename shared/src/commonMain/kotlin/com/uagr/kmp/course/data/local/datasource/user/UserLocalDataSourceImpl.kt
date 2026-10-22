/*
// UserLocalDataSourceImpl.kt
// Created by Karla Judith Cervantes Higareda on 08/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.data.local.datasource.user

import com.uagr.kmp.course.data.local.database.dao.user.UserDao
import com.uagr.kmp.course.data.local.datastore.AppDataStore
import com.uagr.kmp.course.domain.mapper.user.toEntity
import com.uagr.kmp.course.domain.mapper.user.toDomain
import com.uagr.kmp.course.domain.model.register.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class UserLocalDataSourceImpl(
    private val userDao: UserDao,
    private val appDataStore: AppDataStore,
) : UserLocalDataSource {
    override fun getUser(): Flow<UserModel?> = userDao.getUser().map { user ->
        user?.toDomain()
    }

    override suspend fun insertUserAndDelete(user: UserModel) {
        userDao.insertUserAndDeleteOld(user = user.toEntity())
    }
    override suspend fun saveUserToken(token: String) {
        appDataStore.saveUserToken(token = token)
    }
}
