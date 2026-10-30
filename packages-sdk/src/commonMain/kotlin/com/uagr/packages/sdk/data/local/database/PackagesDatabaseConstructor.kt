/*
 * PackagesDatabaseConstructor.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.data.local.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object PackagesDatabaseConstructor : RoomDatabaseConstructor<PackagesDatabase> {
    override fun initialize(): PackagesDatabase
}
