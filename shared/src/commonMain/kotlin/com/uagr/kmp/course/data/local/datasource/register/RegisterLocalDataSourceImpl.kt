package com.uagr.kmp.course.data.local.datasource.register

import com.uagr.kmp.course.data.local.database.dao.register.UserDao
import com.uagr.kmp.course.data.local.datasource.login.RegisterLocalDataSource
import com.uagr.kmp.course.data.local.datastore.AppDataStore
import com.uagr.kmp.course.domain.mapper.register.toDomain
import com.uagr.kmp.course.domain.mapper.register.toEntity
import com.uagr.kmp.course.domain.model.register.UserModel

class RegisterLocalDataSourceImpl(
    private val userDao: UserDao,
): RegisterLocalDataSource{
    override suspend fun insertUserAndDeleteOld(user: UserModel){
        userDao.insertUserAndDeleteOld(user = user.toEntity())
    }

    override suspend fun getUser(): UserModel? {
       return userDao.getUser()?.toDomain()
    }

}
