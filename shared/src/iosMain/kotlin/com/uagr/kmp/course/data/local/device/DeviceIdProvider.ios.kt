/*
 * DeviceIdProvider.ios.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.local.device

import com.uagr.kmp.course.utils.constant.Constants
import platform.Foundation.NSUUID
import platform.Foundation.NSUserDefaults
import platform.UIKit.UIDevice

actual fun createDeviceIdProvider(): DeviceIdProvider = IosDeviceIdProvider()

private class IosDeviceIdProvider : DeviceIdProvider {

    private val defaults = NSUserDefaults.standardUserDefaults

    override fun getDeviceId(): String {
        defaults.stringForKey(Constants.DEVICE_ID_KEY)
            ?.takeIf { id -> isValidUuid(id) }
            ?.let { existing -> return existing }

        val vendorId = UIDevice.currentDevice.identifierForVendor?.UUIDString
        val deviceId = vendorId?.takeIf { id -> isValidUuid(id) } ?: NSUUID().UUIDString
        defaults.setObject(deviceId, forKey = Constants.DEVICE_ID_KEY)
        return deviceId
    }
}

private fun isValidUuid(value: String): Boolean =
    UUID_REGEX.matches(value)

private val UUID_REGEX = Regex(
    pattern = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
)
