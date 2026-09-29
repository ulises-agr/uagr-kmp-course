/*
 * DeviceIdProvider.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.local.device

/**
 * Stable device identifier for auth requests (device_id).
 * Platform-specific: Android ID / iOS identifierForVendor (with fallback UUID).
 */
interface DeviceIdProvider {
    fun getDeviceId(): String
}

expect fun createDeviceIdProvider(): DeviceIdProvider
