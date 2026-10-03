/*
// RegisterRepositoryImpl.kt
// Created by Karla Judith Cervantes Higareda on 29/09/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.data.repository.register

import com.uagr.kmp.course.data.network.datasource.register.RegisterNetworkDataSource
import com.uagr.kmp.course.data.network.model.request.RegisterRequest
import com.uagr.kmp.course.domain.model.register.RegisterModel
import com.uagr.kmp.course.domain.repository.register.RegisterRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RegisterRepositoryImpl(
    private val registerNetworkDataSource: RegisterNetworkDataSource,
    private val dispatcher: CoroutineDispatcher,
) : RegisterRepository {

    override suspend fun createRegister(
        url: String,
        registerRequest: RegisterRequest,
    ): Flow<NetworkResult<RegisterModel>> = flow {
        emit(
            registerNetworkDataSource.createRegister(
                url = url,
                registerRequest = registerRequest,
            )
        )
    }.flowOn(context = dispatcher)
}
