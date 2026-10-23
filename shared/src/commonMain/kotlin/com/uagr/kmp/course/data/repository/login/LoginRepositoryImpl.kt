package com.uagr.kmp.course.data.repository.bone

import com.uagr.kmp.course.data.local.datasource.bone.LoginNetworkDataSource
import com.uagr.kmp.course.data.network.model.request.login.LoginRequest
import com.uagr.kmp.course.domain.model.login.TokensModel
import com.uagr.kmp.course.domain.repository.bone.LoginRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginRepositoryImpl(
    private val loginNetworkDataSource: LoginNetworkDataSource,
    private val dispatcher: CoroutineDispatcher,
): LoginRepository{
    override suspend fun login(
        url: String,
        loginRequest: LoginRequest
    ): Flow<NetworkResult<TokensModel>> = flow {
        emit(loginNetworkDataSource.login(
            url = url,
            loginRequest = loginRequest
        ))
    }.flowOn(dispatcher)
}