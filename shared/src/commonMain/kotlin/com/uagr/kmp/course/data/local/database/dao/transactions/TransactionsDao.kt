package com.uagr.kmp.course.data.local.database.dao.transactions

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import androidx.room3.Transaction
import com.uagr.kmp.course.data.local.model.transaction.TransactionsEntity

@Dao
interface TransactionsDao {
    @Transaction
    suspend fun insertTransactionsDao(transaction: TransactionsEntity){
        deleteAllUsers()
        insertTransaction(transaction)
    }
    @Query("DELETE FROM transactions")
    suspend fun deleteAllUsers()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionsEntity): Long

    @Query("SELECT * FROM transactions LIMIT 1")
    suspend fun getTransaction(): TransactionsEntity?
}