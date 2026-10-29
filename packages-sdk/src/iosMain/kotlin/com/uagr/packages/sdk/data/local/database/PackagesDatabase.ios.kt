/*
 * PackagesDatabase.ios.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.data.local.database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSApplicationSupportDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual fun getPackagesDatabaseBuilder(): RoomDatabase.Builder<PackagesDatabase> =
    Room.databaseBuilder<PackagesDatabase>(
        name = applicationSupportDirectory() + "/$PACKAGES_DATABASE_NAME",
    ).setDriver(BundledSQLiteDriver())

@OptIn(ExperimentalForeignApi::class)
private fun applicationSupportDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSApplicationSupportDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}
