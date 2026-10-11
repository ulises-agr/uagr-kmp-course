/*
 * CoreModule.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.uagr.kmp.course.data.local.database.AppDatabase
import com.uagr.kmp.course.data.local.database.getDatabaseBuilder
import com.uagr.kmp.course.data.local.datastore.AppDataStore
import com.uagr.kmp.course.data.local.datastore.createDataStore
import com.uagr.kmp.course.data.network.client.createHttpClient
import com.uagr.kmp.course.data.repository.login.LoginRepositoryImpl
import com.uagr.kmp.course.domain.repository.login.LoginRepository
import com.uagr.kmp.course.domain.usecase.login.LoginUseCase
import com.uagr.kmp.course.presentation.ui.login.viewmodel.LoginViewModel
import com.uagr.kmp.course.presentation.ui.register.viewmodel.RegisterViewModel
import com.uagr.kmp.course.presentation.ui.welcome.viewmodel.WelcomeViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module
import com.uagr.kmp.course.data.repository.register.RegisterRepositoryImpl
import com.uagr.kmp.course.domain.repository.register.RegisterRepository
import com.uagr.kmp.course.domain.usecase.register.RegisterUseCase
import com.uagr.kmp.course.data.network.datasource.login.LoginRemoteDataSource
import com.uagr.kmp.course.data.network.datasource.register.RegisterRemoteDataSource
import com.uagr.kmp.course.domain.usecase.login.ValidateLoginUseCase
import com.uagr.kmp.course.domain.usecase.register.ValidateRegisterUseCase

val dispatcherModule = module {
    single<CoroutineDispatcher> { Dispatchers.IO }
}

val databaseModule = module {
    single<AppDatabase> {
        getDatabaseBuilder()
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}

val dataStoreModule = module {
    single<DataStore<Preferences>> { createDataStore() }
    singleOf(constructor = ::AppDataStore)
}

val databaseDaoModule = module {

}

val networkModule = module {
    single { createHttpClient(appDataStore = get()) }
}

val dataSourceRemoteModule = module {
    singleOf(::LoginRemoteDataSource)
    singleOf(::RegisterRemoteDataSource)
}

val dataSourceLocalModule = module {

}

val repositoryModule = module {
    singleOf(::LoginRepositoryImpl) bind LoginRepository::class
    singleOf(::RegisterRepositoryImpl) bind RegisterRepository::class
}

val useCaseModule = module {
    factoryOf(::LoginUseCase)
    factoryOf(::ValidateLoginUseCase)
    factoryOf(::RegisterUseCase)
    factoryOf(::ValidateRegisterUseCase)
}

val viewmodelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
}

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            dispatcherModule,
            databaseModule,
            dataStoreModule,
            databaseDaoModule,
            networkModule,
            dataSourceRemoteModule,
            dataSourceLocalModule,
            repositoryModule,
            useCaseModule,
            viewmodelModule,
        )
    }
}
