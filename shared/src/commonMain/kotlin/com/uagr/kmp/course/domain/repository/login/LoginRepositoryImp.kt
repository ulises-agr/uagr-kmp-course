/*
 * LoginRepositoryImp.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.repository.login

import com.uagr.kmp.course.data.network.datasource.login.LoginNetworkDataSource
import com.uagr.kmp.course.data.network.model.login.request.LoginRequest
import com.uagr.kmp.course.domain.model.login.LoginModel
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginRepositoryImp(
    private val loginNetworkDataSource: LoginNetworkDataSource,
    private val dispatcher: CoroutineDispatcher
) : LoginRepository{
    
    override suspend fun login(
        url: String,
        loginRequest: LoginRequest,
    ): Flow<NetworkResult<LoginModel>> = flow {
        emit(
            value = loginNetworkDataSource.login(
                url = url,
                loginRequest = loginRequest
            )
        )
    }.flowOn(context = dispatcher)
    
}