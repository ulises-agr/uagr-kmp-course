/*
 * RegisterRepositoryImpl.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.repository.register

import com.uagr.kmp.course.data.network.datasource.register.RegisterNetworkDataSource
import com.uagr.kmp.course.data.network.model.request.register.RegisterRequest
import com.uagr.kmp.course.domain.repository.register.RegisterRepository

class RegisterRepositoryImpl(
    private val registerNetworkDataSource: RegisterNetworkDataSource
) : RegisterRepository {

    override suspend fun registerUser(name: String, email: String, password: String): Result<Unit> {
        return runCatching {
            registerNetworkDataSource.register(
                RegisterRequest(name = name, email = email, password = password)
            )
        }
    }
}