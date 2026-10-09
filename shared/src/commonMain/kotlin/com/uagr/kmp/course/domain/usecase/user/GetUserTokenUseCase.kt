package com.uagr.kmp.course.domain.usecase.user

import com.uagr.kmp.course.domain.model.user.UserTokenModel
import com.uagr.kmp.course.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class GetUserTokenUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(): Flow<String> =
        userRepository.getUserToken()

}