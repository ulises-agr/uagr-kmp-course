/*
 * LoginRepositoryImpl.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.repository.user

import com.uagr.kmp.course.data.local.datasource.user.UsersLocalDataSource
import com.uagr.kmp.course.domain.model.user.UserModel
import com.uagr.kmp.course.domain.repository.user.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepositoryImpl(
    private val usersLocalDataSource: UsersLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher,
): UserRepository {

    override suspend fun clearAndInsertUser(user: UserModel): Flow<Unit> = flow {
        emit(value = usersLocalDataSource.clearAndInsertUser(user = user))
    }.flowOn(context = ioDispatcher)

    override suspend fun getFirstUser(): Flow<UserModel?> = flow {
        emit(value = usersLocalDataSource.getFirstUser())
    }.flowOn(context = ioDispatcher)

    override suspend fun getUserToken(): Flow<String?> =
        usersLocalDataSource.getUserToken().flowOn(context = ioDispatcher)

    override suspend fun saveUserToken(token: String) = flow {
        emit(value = usersLocalDataSource.saveUserToken(token = token))
    }.flowOn(context = ioDispatcher)
}
