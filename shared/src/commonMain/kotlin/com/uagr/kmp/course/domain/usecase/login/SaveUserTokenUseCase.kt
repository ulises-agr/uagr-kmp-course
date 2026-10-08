/*
// SaveUserTokenUseCase.kt
// Created by Karla Judith Cervantes Higareda on 08/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.domain.usecase.login

import com.uagr.kmp.course.data.local.datastore.AppDataStore
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.authProvider
import io.ktor.client.plugins.auth.providers.BearerAuthProvider
import org.koin.core.annotation.Factory

@Factory
class SaveUserTokenUseCase(
    private val appDataStore: AppDataStore,
    private val httpClient: HttpClient,
) {
    suspend operator fun invoke(token: String) {
        require(token.isNotBlank()) { "Invalid access token" }
        appDataStore.saveUserToken(token)
        httpClient.authProvider<BearerAuthProvider>()?.clearToken()
    }
}
