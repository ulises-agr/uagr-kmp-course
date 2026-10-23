package com.uagr.kmp.course.domain.repository.TokenRepository

import com.uagr.kmp.course.domain.model.login.TokensModel
import com.uagr.kmp.course.domain.model.register.UserModel
import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    suspend fun insertTokenAndDelete(token: TokensModel): Flow<Unit>

    suspend fun getToken(): String?
}