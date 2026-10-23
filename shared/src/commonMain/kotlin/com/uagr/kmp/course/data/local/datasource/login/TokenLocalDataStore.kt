package com.uagr.kmp.course.data.local.datasource.login

import com.uagr.kmp.course.domain.model.login.TokensModel
import com.uagr.kmp.course.domain.model.transactions.TransactionsModel

interface TokenLocalDataStore {
    suspend fun insertTokenDelete(token: TokensModel)
    suspend fun getToken(): String?

}