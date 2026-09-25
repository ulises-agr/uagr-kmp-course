package com.uagr.kmp.course.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.uagr.kmp.course.data.local.model.PackagesEntity


@Dao
interface PackageDAO {
    
    @Transaction
    suspend fun clearAndInsertPackages(packages: List<PackagesEntity>){
        deleteAllPackages()
        insertPackage(packages =  packages)
    }
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPackage(packages : List<PackagesEntity>)
    
    @Query("SELECT * FROM packages")
    suspend fun getPackages(): List<PackagesEntity>
    
    @Query("DELETE FROM packages")
    suspend fun deleteAllPackages()
    
    
}