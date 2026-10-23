package com.uagr.kmp.course.data.repository.login

import com.uagr.kmp.course.data.local.datasource.login.TokenLocalDataStore
import com.uagr.kmp.course.domain.model.login.TokensModel
import com.uagr.kmp.course.domain.repository.TokenRepository.TokenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TokenRepositoryImpl(
    private val tokenLocalDataStore: TokenLocalDataStore
) : TokenRepository {

    override suspend fun insertTokenAndDelete(
        token: TokensModel
    ): Flow<Unit> = flow {
        tokenLocalDataStore.insertTokenDelete(token)
        emit(Unit)
    }

    override suspend fun getToken(): String? {
        return tokenLocalDataStore.getToken()
    }

}