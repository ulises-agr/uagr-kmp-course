/*
 * SecureTokenStorage.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.local.secure

/**
 * Platform-backed secure storage for auth tokens.
 * Android → EncryptedSharedPreferences (Keystore).
 * iOS → Keychain.
 */
interface SecureTokenStorage {
    suspend fun saveToken(token: String)
    suspend fun getToken(): String?
    suspend fun clearToken()
}

expect fun createSecureTokenStorage(): SecureTokenStorage
