package com.uagr.kmp.course.di

import com.uagr.kmp.course.data.local.database.AppDatabase
import com.uagr.kmp.course.data.local.database.getDatabaseBuilder
import com.uagr.kmp.course.data.local.datastore.AppDataStore
import com.uagr.kmp.course.data.local.datastore.createDataStore
import com.uagr.kmp.course.data.network.client.createHttpClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.uagr.kmp")
@Configuration
class KoinModules {
    @Single
    fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Single
    fun httpClient(appDataStore: AppDataStore) = createHttpClient(appDataStore)

    @Single
    fun appDatabase() = getDatabaseBuilder()
        .addMigrations(AppDatabase.MIGRATION_1_2)
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()

    @Single
    fun userDao(appDatabase: AppDatabase) = appDatabase.userDao()

    @Single
    fun dataStore() = createDataStore()
}
