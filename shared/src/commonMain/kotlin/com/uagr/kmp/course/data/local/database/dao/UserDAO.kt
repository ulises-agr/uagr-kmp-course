/*
 * UserDao.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.uagr.kmp.course.data.local.model.UserEntity

@Dao
interface UserDAO {
    
    @Transaction
    suspend fun insertAndDeleteUser(user: UserEntity){
            deleteUsers()
            insertUser(user = user)
    }
    
    @Query("DELETE FROM users")
    suspend fun deleteUsers()
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user : UserEntity)
    
}