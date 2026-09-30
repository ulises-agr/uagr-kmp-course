/*
 * RegisterRepository.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.repository.register

interface RegisterRepository {
    suspend fun registerUser(name: String, email: String, password: String): Result<Unit>
}
