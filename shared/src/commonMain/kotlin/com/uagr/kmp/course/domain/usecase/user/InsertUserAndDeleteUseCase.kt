/*
 * InsertUserAndDeleteUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase.user

import com.uagr.kmp.course.domain.model.user.userModel
import com.uagr.kmp.course.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class InsertUserAndDeleteUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(user: userModel?): Flow<Unit> =
        userRepository.insertUserAndDelete(user = user!!)

}
