/*
 * RegisterNetworkDataSource.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.network.datasource.register

import com.uagr.kmp.course.data.network.model.request.register.RegisterRequest
import com.uagr.kmp.course.data.network.model.response.register.RegisterResponse

interface RegisterNetworkDataSource {
    suspend fun register(request: RegisterRequest): RegisterResponse
}