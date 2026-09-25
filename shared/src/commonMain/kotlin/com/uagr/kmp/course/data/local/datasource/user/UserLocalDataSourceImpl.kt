package com.uagr.kmp.course.data.local.datasource.user

import com.uagr.kmp.course.data.local.database.dao.UserDAO
import com.uagr.kmp.course.data.local.datastore.AppDataStore
import com.uagr.kmp.course.domain.mapper.user.toEntity
import com.uagr.kmp.course.domain.model.login.UserModel
import org.koin.core.annotation.Factory

@Factory
class UserLocalDataSourceImpl(
    private val userDAO: UserDAO,
    private val appDataStore: AppDataStore
) : UserLocalDataSource {
    
    override suspend fun insertUser(user : UserModel) {
        userDAO.insertAndDeleteUser(user = user.toEntity())
    }
    
    override suspend fun insertUserToken(token: String) {
        appDataStore.saveUserToken(token = token)
    }
    
}