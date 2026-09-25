package com.uagr.kmp.course.data.local.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.uagr.kmp.course.data.local.database.dao.PackageDAO
import com.uagr.kmp.course.data.local.database.dao.UserDAO
import com.uagr.kmp.course.data.local.model.PackagesEntity
import com.uagr.kmp.course.data.local.model.UserEntity

@Database(
    entities = [
        PackagesEntity::class,
        UserEntity::class
    ],
    version = 3,
    exportSchema = false
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun packageDAO() : PackageDAO
    
    abstract fun userDao(): UserDAO
    
     companion object{
        const val DATABASE_NAME = "kmp_course_db"
     }
}

expect fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>