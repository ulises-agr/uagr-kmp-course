package com.uagr.kmp.course.domain.usecase.user

import com.uagr.kmp.course.domain.model.register.UserModel
import com.uagr.kmp.course.domain.repository.repository.RegisterRepository
import kotlinx.coroutines.flow.Flow

class GetUserCase(
    private val registerRepository: RegisterRepository,
){
    suspend operator fun invoke(): UserModel? {
        return registerRepository.getUser()
    }
}