/*
 * PackagesHttpClient.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.data.network.client

import com.uagr.packages.sdk.config.PackagesSdkConfig
import io.ktor.client.HttpClient

expect fun createPackagesHttpClient(config: PackagesSdkConfig): HttpClient
