package com.uagr.kmp.course.data.repository.register

import com.uagr.kmp.course.data.local.datasource.login.RegisterLocalDataSource
import com.uagr.kmp.course.data.network.datasource.register.RegisterNetworkDataSource
import com.uagr.kmp.course.data.network.model.request.register.RegisterRequest
import com.uagr.kmp.course.domain.model.register.RegisterModel
import com.uagr.kmp.course.domain.model.register.UserModel
import com.uagr.kmp.course.domain.repository.repository.RegisterRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RegisterRepositoryImpl(
    private val registerNetworkDataSource: RegisterNetworkDataSource,
    private val userLocalDataSource: RegisterLocalDataSource,
    private val dispatcher: CoroutineDispatcher,
): RegisterRepository{
    override suspend fun register(
        url: String,
        registerRequest: RegisterRequest
    ): Flow<NetworkResult<RegisterModel>> = flow {
        emit(registerNetworkDataSource.register(
            url = url,
            registerRequest = registerRequest
        ))
    }.flowOn(dispatcher)

    override suspend fun insertUserAndDeleteOld(user: UserModel): Flow<Unit> = flow {
        emit(value = userLocalDataSource.insertUserAndDeleteOld(user))
    }.flowOn(dispatcher)

    override suspend fun getUser(): UserModel? =
       userLocalDataSource.getUser()
}