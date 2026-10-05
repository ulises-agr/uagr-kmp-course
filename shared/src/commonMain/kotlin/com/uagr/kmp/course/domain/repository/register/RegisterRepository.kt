package com.uagr.kmp.course.domain.repository.register

interface RegisterRepository {

    suspend fun register(
        name: String,
        email: String,
        password: String
    ): Boolean
}