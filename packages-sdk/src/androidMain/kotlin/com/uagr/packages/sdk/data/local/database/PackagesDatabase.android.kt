/*
 * PackagesDatabase.android.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.data.local.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.mp.KoinPlatform.getKoin

actual fun getPackagesDatabaseBuilder(): RoomDatabase.Builder<PackagesDatabase> {
    val context = getKoin().get<Context>()
    val dbFile = context.getDatabasePath(PACKAGES_DATABASE_NAME)
    return Room.databaseBuilder<PackagesDatabase>(
        context = context,
        name = dbFile.absolutePath,
    )
}
