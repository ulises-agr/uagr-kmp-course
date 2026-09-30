package com.uagr.kmp.course.data.repository.login

import com.uagr.kmp.course.domain.repository.login.LoginRepository
import kotlinx.coroutines.delay

class LoginRepositoryImpl : LoginRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Boolean {

        delay(1500)

        return true
    }
}