package com.uagr.kmp.course.domain.usecase.user

import com.uagr.kmp.course.domain.repository.TokenRepository.TokenRepository

class GetUserTokenCase(
    val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(): String? {
        return tokenRepository.getToken()
    }
}