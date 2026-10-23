package com.uagr.kmp.course.data.local.database.dao.register

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import androidx.room3.Transaction
import com.uagr.kmp.course.data.local.model.register.UserEntity

@Dao
interface UserDao {
    @Transaction
    suspend fun insertUserAndDeleteOld(user: UserEntity){
        deleteAllUsers()
        insertUser(user)
    }
    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity): Long

    @Query("SELECT * FROM users LIMIT 1")
    suspend fun getUser(): UserEntity?
}