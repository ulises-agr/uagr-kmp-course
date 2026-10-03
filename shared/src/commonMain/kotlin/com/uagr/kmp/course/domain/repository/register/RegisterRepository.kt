/*
// RegisterRepository.kt
// Created by Karla Judith Cervantes Higareda on 01/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.domain.repository.register

import com.uagr.kmp.course.data.network.model.request.RegisterRequest
import com.uagr.kmp.course.domain.model.register.RegisterModel
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    suspend fun createRegister(
        url: String,
        request: RegisterRequest
    ): Flow<NetworkResult<RegisterModel>>
}
