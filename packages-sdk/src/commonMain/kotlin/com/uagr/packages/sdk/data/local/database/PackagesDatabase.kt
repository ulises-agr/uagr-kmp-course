/*
 * PackagesDatabase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.data.local.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.uagr.packages.sdk.data.local.dao.PackagesDao
import com.uagr.packages.sdk.data.local.entity.PackagesEntity

internal const val PACKAGES_DATABASE_NAME = "packages_sdk.db"

@Database(
    entities = [PackagesEntity::class],
    version = 1,
    exportSchema = false,
)
@ConstructedBy(PackagesDatabaseConstructor::class)
abstract class PackagesDatabase : RoomDatabase() {
    abstract fun packagesDao(): PackagesDao
}

expect fun getPackagesDatabaseBuilder(): RoomDatabase.Builder<PackagesDatabase>
