package com.uagr.kmp.course.data.local.datasource.login

import com.uagr.kmp.course.data.local.datastore.AppDataStore
import com.uagr.kmp.course.domain.model.login.TokensModel
import com.uagr.kmp.course.domain.model.transactions.TransactionsModel
import kotlinx.coroutines.flow.first

class TokenLocalDataStoreImpl(
    private val appDataStore: AppDataStore,
): TokenLocalDataStore{
    override suspend fun insertTokenDelete(token: TokensModel) {
        appDataStore.saveUserToken(token = token.access_token)
    }

    override suspend fun getToken(): String? {
       return appDataStore.userToken.first()
    }

}