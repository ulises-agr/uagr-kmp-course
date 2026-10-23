package com.uagr.kmp.course.domain.usecase.transactions

import com.uagr.kmp.course.domain.model.transactions.TransactionsModel
import com.uagr.kmp.course.domain.repository.transactions.TransactionsRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

class GetAccountsUseCase(
    val translationsRepository: TransactionsRepository
) {
    suspend  fun callService(
        url: String,
        access_token: String,
    ) : Flow<NetworkResult<TransactionsModel>> =
        translationsRepository.callService(
            url = url,
            access_token = access_token
        )

    suspend fun getTranslactions(): TransactionsModel? {
        return translationsRepository.getTransactions()
    }
}
