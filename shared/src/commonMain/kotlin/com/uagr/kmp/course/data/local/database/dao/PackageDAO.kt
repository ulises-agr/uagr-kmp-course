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
    suspend fun clearAndInsertPackages(packagesEntity: PackagesEntity){
        deleteAllPackages()
        insertPackage(packagesEntity =  packagesEntity)
    }
    
    @Query("SELECT * FROM packages")
    suspend fun getPackage() : PackagesEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPackage(packagesEntity: PackagesEntity)
    
    @Query("DELETE FROM packages")
    suspend fun deleteAllPackages()
    
    
}