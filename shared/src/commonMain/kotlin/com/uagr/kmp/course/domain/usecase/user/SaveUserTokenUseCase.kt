/*
 * SaveUserTokenUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase.user

import com.uagr.kmp.course.domain.model.user.UserTokensModel
import com.uagr.kmp.course.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow

class SaveUserTokenUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(token: UserTokensModel?): Flow<Unit> =
        if (!token?.access_token.isNullOrEmpty()) {
            userRepository.saveUserToken(token = token.access_token)
        } else {
            throw IllegalArgumentException()
        }
}
