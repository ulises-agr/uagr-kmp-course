package com.uagr.kmp.course.data.local.database.dao.dashboard

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import androidx.room3.Transaction
import com.uagr.kmp.course.data.local.model.dashboard.DashboardEntity

@Dao
interface DashboardDao {
    @Transaction
    suspend fun insertDashboardEntityAndDelete(dashboard: DashboardEntity){
        deleteAllDashboard()
        insertDashboard(dashboard)
    }
    @Query("DELETE FROM dashboard")
    suspend fun deleteAllDashboard()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDashboard(dashboard: DashboardEntity): Long

    @Query("SELECT * FROM dashboard LIMIT 1")
    suspend fun getDashboard(): DashboardEntity?
}