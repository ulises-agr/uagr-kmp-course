package com.uagr.kmp.course.domain.usecase.user

import com.uagr.kmp.course.domain.model.user.UserModel
import com.uagr.kmp.course.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory


@Factory
class GetLocalUserUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(): Flow<UserModel> =
        userRepository.getUser()

}