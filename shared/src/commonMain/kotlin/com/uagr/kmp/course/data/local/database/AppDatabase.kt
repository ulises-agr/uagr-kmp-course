/*
 * AppDatabase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.local.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.uagr.kmp.course.data.local.database.dao.users.UsersDao
import com.uagr.kmp.course.data.local.model.user.UserEntity

@Database(
    entities = [
        UserEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UsersDao

    companion object {
        const val DATABASE_NAME = "kmp_course_DB"
    }
}

expect fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>
