package com.uagr.kmp.course.domain.repository.repository

import com.uagr.kmp.course.data.network.model.request.register.RegisterRequest
import com.uagr.kmp.course.domain.model.register.RegisterModel
import com.uagr.kmp.course.domain.model.register.UserModel
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    suspend fun register(
        url: String,
        registerRequest: RegisterRequest,
    ): Flow<NetworkResult<RegisterModel>>
    suspend fun insertUserAndDeleteOld(user: UserModel): Flow<Unit>

    suspend fun getUser(): UserModel?
}