package com.uagr.kmp.course.domain.repository.user

import com.uagr.kmp.course.domain.model.login.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun insertUser(user : UserModel) : Flow<Unit>
    
    suspend fun saveUserToken(token : String) : Flow<Unit>
}