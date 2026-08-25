/*
 * LoginRepositoryImpl.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.repository.login

import com.uagr.kmp.course.data.network.datasource.login.LoginRemoteDataSource
import com.uagr.kmp.course.domain.model.login.LoginCredentialsModel
import com.uagr.kmp.course.domain.model.login.LoginDataModel
import com.uagr.kmp.course.domain.repository.login.LoginRepository
import com.uagr.kmp.course.util.network.api.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginRepositoryImpl(
    private val loginRemoteDataSource: LoginRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher,
): LoginRepository {

    override suspend fun login(
        url: String,
        loginCredentials: LoginCredentialsModel,
    ): Flow<NetworkResult<LoginDataModel>> = flow {
        emit(
            value = loginRemoteDataSource.login(
                url = url,
                loginCredentialsModel = loginCredentials,
            ),
        )
    }.flowOn(context = ioDispatcher)
}
