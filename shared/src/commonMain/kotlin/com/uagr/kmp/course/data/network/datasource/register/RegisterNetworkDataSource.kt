/*
// RegisterNetworkDataSource.kt
// Created by Karla Judith Cervantes Higareda on 29/09/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.data.network.datasource.register

import com.uagr.kmp.course.data.network.model.request.RegisterRequest
import com.uagr.kmp.course.domain.model.register.RegisterModel
import com.uagr.kmp.course.utils.network.NetworkResult
interface RegisterNetworkDataSource {
    suspend fun createRegister(
        url: String,
        registerRequest: RegisterRequest,
    ): NetworkResult<RegisterModel>
}
