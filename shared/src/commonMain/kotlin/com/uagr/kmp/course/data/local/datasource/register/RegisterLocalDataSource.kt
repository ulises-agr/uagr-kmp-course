package com.uagr.kmp.course.data.local.datasource.login

import com.uagr.kmp.course.domain.model.register.UserModel

interface RegisterLocalDataSource {
    suspend fun insertUserAndDeleteOld(user: UserModel)
    suspend fun getUser(): UserModel?
}