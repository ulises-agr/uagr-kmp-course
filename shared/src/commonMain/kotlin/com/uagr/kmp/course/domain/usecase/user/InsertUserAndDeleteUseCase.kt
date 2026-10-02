package com.uagr.kmp.course.domain.usecase.user

import com.uagr.kmp.course.domain.model.user.UserModel
import com.uagr.kmp.course.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.annotation.Factory

@Factory
class InsertUserAndDeleteUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(user: UserModel?): Flow<Unit> =
        if (user != null) {
            userRepository.insertUserAndDelete(user = user)
        } else {
            flowOf(Unit)
        }
}
