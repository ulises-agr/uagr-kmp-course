/*
 * DeviceIdProvider.android.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.local.device

import android.content.Context
import com.uagr.kmp.course.utils.constant.Constants
import org.koin.mp.KoinPlatform.getKoin
import java.util.UUID

actual fun createDeviceIdProvider(): DeviceIdProvider =
    AndroidDeviceIdProvider(context = getKoin().get())

private class AndroidDeviceIdProvider(
    context: Context,
) : DeviceIdProvider {

    private val prefs = context.getSharedPreferences(
        Constants.DEVICE_PREFS_NAME,
        Context.MODE_PRIVATE,
    )

    override fun getDeviceId(): String {
        prefs.getString(Constants.DEVICE_ID_KEY, null)
            ?.takeIf { id -> isValidUuid(id) }
            ?.let { existing -> return existing }

        val deviceId = UUID.randomUUID().toString()
        prefs.edit().putString(Constants.DEVICE_ID_KEY, deviceId).apply()
        return deviceId
    }
}

private fun isValidUuid(value: String): Boolean =
    runCatching { UUID.fromString(value) }.isSuccess
