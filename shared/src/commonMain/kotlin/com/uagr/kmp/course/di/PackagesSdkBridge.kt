/*
 * PackagesSdkBridge.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.di

import com.uagr.kmp.course.data.local.datastore.AppDataStore
import com.uagr.kmp.course.utils.constant.NetworkUrl
import com.uagr.packages.sdk.PackagesSdk
import com.uagr.packages.sdk.config.PackagesTokenProvider
import com.uagr.packages.sdk.config.PackagesTokens
import kotlinx.coroutines.flow.first
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Host bridge: supplies tokens from [AppDataStore] and loads the Packages SDK
 * with the app [NetworkUrl] base / endpoint (swap freely per environment).
 */
fun packagesSdkHostModule(): Module = module {
    single<PackagesTokenProvider> {
        val store = get<AppDataStore>()
        PackagesTokenProvider {
            val token = store.userToken.first()
            if (!token.isNullOrBlank()) {
                PackagesTokens(accessToken = token)
            } else {
                null
            }
        }
    }

    includes(
        PackagesSdk.module(
            baseUrl = NetworkUrl.BASE_URL,
            productsEndpoint = NetworkUrl.GET_PACKAGES_ENDPOINT,
        ),
    )
}
