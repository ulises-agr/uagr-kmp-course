/*
 * AppDatabase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.local.database

import androidx.room3.ConstructedBy
import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.uagr.kmp.course.data.local.model.bone.BoneEntity

@Database(
    entities = [
        BoneEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase: RoomDatabase() {

}

expect fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>
