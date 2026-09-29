/*
 * KoinModules.kt
 * Copyright (c) 2026. All rights reserved
*/
package com.uagr.kmp.course.di

import com.uagr.kmp.course.data.local.database.AppDatabase
import com.uagr.kmp.course.data.local.database.getDatabaseBuilder
import com.uagr.kmp.course.data.local.datastore.createDataStore
import com.uagr.kmp.course.data.local.device.DeviceIdProvider
import com.uagr.kmp.course.data.local.device.createDeviceIdProvider
import com.uagr.kmp.course.data.local.secure.SecureTokenStorage
import com.uagr.kmp.course.data.local.secure.createSecureTokenStorage
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
    fun secureTokenStorage(): SecureTokenStorage = createSecureTokenStorage()

    @Single
    fun deviceIdProvider(): DeviceIdProvider = createDeviceIdProvider()

    @Single
    fun httpClient(secureTokenStorage: SecureTokenStorage) =
        createHttpClient(secureTokenStorage = secureTokenStorage)

    @Single
    fun appDatabase() = getDatabaseBuilder()
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()

    @Single
    fun userDao(database: AppDatabase) = database.userDao()

    @Single
    fun dataStore() = createDataStore()
}
