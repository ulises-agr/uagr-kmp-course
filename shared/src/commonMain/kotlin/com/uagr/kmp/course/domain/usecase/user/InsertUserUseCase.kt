package com.uagr.kmp.course.domain.usecase.user

import com.uagr.kmp.course.domain.model.login.UserModel
import com.uagr.kmp.course.domain.repository.login.LoginRepository
import com.uagr.kmp.course.domain.repository.user.UserRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class InsertUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user : UserModel?) : Flow<Unit> =
        userRepository.insertUser(user = user!!)
    
}