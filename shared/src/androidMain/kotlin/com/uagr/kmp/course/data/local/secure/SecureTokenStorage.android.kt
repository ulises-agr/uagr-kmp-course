/*
 * SecureTokenStorage.android.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.local.secure

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.uagr.kmp.course.utils.constant.Constants
import org.koin.mp.KoinPlatform.getKoin

actual fun createSecureTokenStorage(): SecureTokenStorage =
    AndroidSecureTokenStorage(context = getKoin().get())

private class AndroidSecureTokenStorage(
    context: Context,
) : SecureTokenStorage {

    private val prefs: SharedPreferences = EncryptedSharedPreferences.create(
        Constants.SECURE_PREFS_NAME,
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
    )

    override suspend fun saveToken(token: String) {
        prefs.edit().putString(Constants.USER_TOKEN, token).apply()
    }

    override suspend fun getToken(): String? =
        prefs.getString(Constants.USER_TOKEN, null)

    override suspend fun clearToken() {
        prefs.edit().remove(Constants.USER_TOKEN).apply()
    }
}
