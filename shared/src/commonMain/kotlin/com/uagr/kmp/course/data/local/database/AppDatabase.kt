/*
 * AppDatabase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.local.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.uagr.kmp.course.data.local.database.dao.PackagesDao
import com.uagr.kmp.course.data.local.model.PackagesEntity

@Database(
    entities = [
        PackagesEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun packagesDao(): PackagesDao

    companion object {
        const val DATABASE_NAME = "kmp_course_DB"
    }
}

expect fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>
