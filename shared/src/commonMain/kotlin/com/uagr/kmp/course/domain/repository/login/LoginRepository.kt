package com.uagr.kmp.course.domain.repository.login

interface LoginRepository {

    suspend fun login(
        email: String,
        password: String
    ): Boolean
}