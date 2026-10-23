package com.uagr.kmp.course.domain.usecase.user

import com.uagr.kmp.course.domain.model.login.TokensModel
import com.uagr.kmp.course.domain.repository.TokenRepository.TokenRepository
import kotlinx.coroutines.flow.Flow

class SaveTokenUseCase(
    private val tokenRepository: TokenRepository,
) {
    suspend operator fun invoke(token: TokensModel): Flow<Unit> =
        tokenRepository.insertTokenAndDelete(token = token)
}
