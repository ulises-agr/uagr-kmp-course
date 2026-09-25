package com.uagr.kmp.course.data.repository.user

import com.uagr.kmp.course.data.local.database.dao.UserDAO
import com.uagr.kmp.course.data.local.datasource.user.UserLocalDataSource
import com.uagr.kmp.course.domain.model.login.UserModel
import com.uagr.kmp.course.domain.repository.user.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory

@Factory
class UserRepositoryImpl(
    private val userLocalDataSource: UserLocalDataSource,
    private val dispatcher: CoroutineDispatcher
) : UserRepository {
    
    override suspend fun insertUser(user: UserModel): Flow<Unit> = flow {
        emit(userLocalDataSource.insertUser(user = user))
    }.flowOn(context = dispatcher)
    
    override suspend fun saveUserToken(token: String): Flow<Unit> = flow {
        emit(userLocalDataSource.insertUserToken(token = token))
    }.flowOn(context = dispatcher)
    
    
}