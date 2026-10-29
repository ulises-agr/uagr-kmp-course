/*
 * PackagesSdk.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk

import androidx.compose.runtime.Composable
import com.uagr.packages.sdk.config.PackagesSdkConfig
import com.uagr.packages.sdk.di.packagesSdkModule
import com.uagr.packages.sdk.ui.PackagesScreen
import org.koin.core.module.Module

/**
 * Public entry point for the Packages feature SDK.
 *
 * Host apps (Android / iOS / future Web) must:
 * 1. Provide a [com.uagr.packages.sdk.config.PackagesTokenProvider] in Koin
 * 2. Load [module] with base URL (and optional endpoint)
 * 3. Invoke [Content] from navigation / UI host
 */
object PackagesSdk {

    const val VERSION = "1.0.0"

    fun module(
        baseUrl: String,
        productsEndpoint: String = PackagesSdkConfig.DEFAULT_PRODUCTS_ENDPOINT,
    ): Module = packagesSdkModule(
        baseUrl = baseUrl,
        productsEndpoint = productsEndpoint,
    )

    @Composable
    fun Content(onPackageClick: () -> Unit = {}) {
        PackagesScreen(onPackageClick = onPackageClick)
    }
}
