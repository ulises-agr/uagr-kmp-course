package com.uagr.kmp.course.domain.usecase.user

import com.uagr.kmp.course.domain.model.login.TokensModel
import com.uagr.kmp.course.domain.model.register.UserModel
import com.uagr.kmp.course.domain.repository.TokenRepository.TokenRepository
import com.uagr.kmp.course.domain.repository.repository.RegisterRepository
import kotlinx.coroutines.flow.Flow

class SaveUserUseCase(
    private val registerRepository: RegisterRepository,
){
    suspend operator fun invoke(user: UserModel): Flow<Unit> =
        registerRepository.insertUserAndDeleteOld(user = user)
}