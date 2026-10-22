/*
// GetUserUseCase.kt
// Created by Karla Judith Cervantes Higareda on 09/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.domain.usecase.user

import com.uagr.kmp.course.domain.model.register.UserModel
import com.uagr.kmp.course.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class GetUserUseCase(
    private val userRepository: UserRepository,
) {
    operator fun invoke(): Flow<UserModel?> = userRepository.getUser()
}
