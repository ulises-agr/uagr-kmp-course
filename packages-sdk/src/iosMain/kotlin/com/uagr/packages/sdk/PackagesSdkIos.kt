/*
 * PackagesSdkIos.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.packages.sdk

import androidx.compose.ui.window.ComposeUIViewController
import com.uagr.packages.sdk.config.PackagesTokenProvider
import com.uagr.packages.sdk.config.PackagesTokens
import com.uagr.packages.sdk.di.packagesSdkModule
import org.koin.core.context.startKoin
import org.koin.dsl.module
import platform.UIKit.UIViewController

/**
 * Swift-friendly initializer for PackagesSdk on iOS.
 *
 * @param baseUrl Root URL for API calls.
 * @param accessToken Initial access token string.
 * @param refreshToken Initial refresh token string (optional).
 * @param tokenProviderClosure Optional Swift closure to dynamically fetch tokens on demand.
 */
fun initPackagesSdkForIos(
    baseUrl: String,
    accessToken: String,
    refreshToken: String = "",
    tokenProviderClosure: (() -> PackagesTokens?)? = null,
) {
    val tokenProvider = object : PackagesTokenProvider {
        override suspend fun getTokens(): PackagesTokens? {
            return tokenProviderClosure?.invoke() ?: PackagesTokens(
                accessToken = accessToken,
                refreshToken = refreshToken,
            )
        }
    }

    startKoin {
        modules(
            module {
                single<PackagesTokenProvider> { tokenProvider }
            },
            packagesSdkModule(baseUrl = baseUrl),
        )
    }
}

/**
 * Creates a UIKit [UIViewController] rendering the Packages SDK Compose UI.
 * Can be presented in UIKit or wrapped in SwiftUI via UIViewControllerRepresentable.
 */
fun PackagesViewController(
    onPackageClick: () -> Unit = {},
): UIViewController = ComposeUIViewController {
    PackagesSdk.Content(onPackageClick = onPackageClick)
}
