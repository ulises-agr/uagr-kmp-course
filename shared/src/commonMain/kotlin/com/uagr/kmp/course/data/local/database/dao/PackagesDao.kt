/*
 * PackagesDao.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.uagr.kmp.course.data.local.model.PackagesEntity

@Dao
interface PackagesDao {

    @Transaction
    suspend fun clearAndInsertPackages(packages: PackagesEntity) {
        deleteAllPackages()
        insertPackages(packages = packages)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPackages(packages: PackagesEntity)

    @Query("SELECT * FROM packages")
    suspend fun getPackages(): PackagesEntity?

    @Query("DELETE FROM packages")
    suspend fun deleteAllPackages()
}
