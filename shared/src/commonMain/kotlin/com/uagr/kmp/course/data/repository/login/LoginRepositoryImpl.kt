/*
 * LoginRepositoryImpl.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.repository.login

import com.uagr.kmp.course.data.network.datasource.login.LoginNetworkDataSource
import com.uagr.kmp.course.data.network.model.request.loginRequest
import com.uagr.kmp.course.domain.model.login.loginModel
import com.uagr.kmp.course.domain.repository.login.loginRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory

@Factory
class LoginRepositoryImpl(
    private val loginNetworkDataSource: LoginNetworkDataSource,
    private val dispatcher: CoroutineDispatcher,
): loginRepository {

    override suspend fun login(
        url: String,
        loginRequest: loginRequest,
    ): Flow<NetworkResult<loginModel>> = flow {
        emit(
            loginNetworkDataSource.login(
                url = url,
                loginRequest = loginRequest,
            )
        )
    }.flowOn(context = dispatcher)
}
