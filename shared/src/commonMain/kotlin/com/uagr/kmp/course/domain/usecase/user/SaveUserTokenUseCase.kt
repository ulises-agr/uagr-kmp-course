/*
// SaveUserTokenUseCase.kt
// Created by Karla Judith Cervantes Higareda on 08/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.domain.usecase.user

import com.uagr.kmp.course.domain.model.register.TokensModel
import com.uagr.kmp.course.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class SaveUserTokenUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(token: TokensModel?): Flow<Unit> =
        userRepository.saveUserToken(token = token?.accessToken.orEmpty())
}