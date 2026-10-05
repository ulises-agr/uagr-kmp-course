/*
 * RegisterRepositoryImpl.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.repository.register

import com.uagr.kmp.course.data.network.datasource.register.RegisterNetworkDataSource
import com.uagr.kmp.course.data.network.model.request.register.RegisterRequest
import com.uagr.kmp.course.domain.model.register.RegisterModel
import com.uagr.kmp.course.domain.repository.register.RegisterRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory

@Factory
class RegisterRepositoryImpl(
    private val registerNetworkDataSource: RegisterNetworkDataSource,
    private val ioDispatcher: CoroutineDispatcher,
) : RegisterRepository {

    override suspend fun registerUser(
        url: String,
        registerRequest: RegisterRequest,
    ): Flow<NetworkResult<RegisterModel>> = flow {
        emit(
            registerNetworkDataSource.register(
                url = url,
                request = registerRequest,
            )
        )
    }.flowOn(context = ioDispatcher)
}