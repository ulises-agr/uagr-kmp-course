/*
 * AppDatabase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.local.database

import androidx.room3.ConstructedBy
import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.uagr.kmp.course.data.local.database.dao.dashboard.DashboardDao
import com.uagr.kmp.course.data.local.database.dao.register.UserDao
import com.uagr.kmp.course.data.local.database.dao.transactions.TransactionsDao
import com.uagr.kmp.course.data.local.model.dashboard.DashboardEntity
import com.uagr.kmp.course.data.local.model.register.UserEntity
import com.uagr.kmp.course.data.local.model.transaction.TransactionsEntity

@Database(
    entities = [
        UserEntity::class,
        TransactionsEntity::class,
        DashboardEntity::class,
    ],
    version = 2,
    exportSchema = false,
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun transactionsDao(): TransactionsDao
    abstract fun dashboardDao(): DashboardDao
    companion object{
        const val DATABASE_NAME = "kmp_course_DB"
    }
}

expect fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>
