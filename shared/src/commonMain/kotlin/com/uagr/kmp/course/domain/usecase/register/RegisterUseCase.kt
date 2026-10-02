package com.uagr.kmp.course.domain.usecase.register

import com.uagr.kmp.course.data.network.model.request.RegisterRequest
import com.uagr.kmp.course.domain.model.register.RegisterModel
import com.uagr.kmp.course.domain.repository.register.RegisterRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

/*
// RegisterUseCase.kt
// Created by Karla Judith Cervantes Higareda on 01/10/26.
// Copyright (c) 2026. All rights reserved.
*/

class RegisterUseCase(
    private val repository: RegisterRepository
) {
    suspend operator fun invoke(
        url: String,
        name: String,
        email: String,
        password: String,
    ): Flow<NetworkResult<RegisterModel>> = repository.createRegister(
        url = url,
        request = RegisterRequest(
            name = name.trim(),
            email = email.trim(),
            password = password
        ),
    )
}
