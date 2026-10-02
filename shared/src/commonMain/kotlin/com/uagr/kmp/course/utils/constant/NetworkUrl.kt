/*
 * NetworkUrl.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.utils.constant

object NetworkUrl {

    // Base url
    const val BASE_URL = "https://fintrack-hitss.onrender.com"

    // Endpoint
    const val LOGIN_ENDPOINT = "$BASE_URL/api/v1/auth/login"
    const val REGISTER_ENDPOINT = "$BASE_URL/api/v1/auth/register"
    const val GET_PACKAGES_ENDPOINT = "$BASE_URL/api/v1/products"
}
