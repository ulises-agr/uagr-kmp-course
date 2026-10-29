/*
 * NetworkUrl.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.utils.constant

object NetworkUrl {

    // Base url (injected into PackagesSdk via PackagesSdkBridge — swap per env)
    const val BASE_URL = "https://server-dummy-hitss.onrender.com"

    // Endpoint
    const val LOGIN_ENDPOINT = "api/v1/auth/login"
    const val GET_PACKAGES_ENDPOINT = "api/v1/products"
}
