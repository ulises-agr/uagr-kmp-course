/*
 * CoreModule.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.uagr.kmp.course.data.local.datasource.user.UsersLocalDataSourceImpl
import com.uagr.kmp.course.data.local.database.AppDatabase
import com.uagr.kmp.course.data.local.database.dao.users.UsersDao
import com.uagr.kmp.course.data.local.database.getDatabaseBuilder
import com.uagr.kmp.course.data.local.datasource.user.UsersLocalDataSource
import com.uagr.kmp.course.data.local.datastore.AppDataStore
import com.uagr.kmp.course.data.local.datastore.createDataStore
import com.uagr.kmp.course.data.network.client.createHttpClient
import com.uagr.kmp.course.data.network.datasource.login.LoginRemoteDataSource
import com.uagr.kmp.course.data.network.datasource.login.LoginRemoteDataSourceImpl
import com.uagr.kmp.course.data.repository.login.LoginRepositoryImpl
import com.uagr.kmp.course.data.repository.user.UserRepositoryImpl
import com.uagr.kmp.course.domain.repository.login.LoginRepository
import com.uagr.kmp.course.domain.repository.user.UserRepository
import com.uagr.kmp.course.domain.usecase.login.LoginUseCase
import com.uagr.kmp.course.domain.usecase.login.ValidateLoginUseCase
import com.uagr.kmp.course.domain.usecase.user.ClearAndInsertUserUseCase
import com.uagr.kmp.course.domain.usecase.user.GetFirstUserUseCase
import com.uagr.kmp.course.domain.usecase.user.SaveUserTokenUseCase
import com.uagr.kmp.course.presentation.ui.dashboard.viewmodel.DashboardViewModel
import com.uagr.kmp.course.presentation.ui.login.viewmodel.LoginViewModel
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

val dispatcherModule = module {
    single<CoroutineDispatcher> { Dispatchers.IO }
}

val dataStoreModule = module {
    single<DataStore<Preferences>> { createDataStore() }
    singleOf(::AppDataStore)
}

val databaseModule = module {
    single<AppDatabase> {
        getDatabaseBuilder()
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
    single<UsersDao> { get<AppDatabase>().userDao() }
}

val networkModule = module {
    single { createHttpClient(appDataStore = get()) }
}

val dataSourceRemoteModule = module {
    singleOf(constructor = ::LoginRemoteDataSourceImpl) bind LoginRemoteDataSource::class
}

val dataSourceLocalModule = module {
    singleOf(constructor = ::UsersLocalDataSourceImpl) bind UsersLocalDataSource::class
}

val repositoryModule = module {
    singleOf(constructor = ::LoginRepositoryImpl) bind LoginRepository::class
    singleOf(constructor = ::UserRepositoryImpl) bind UserRepository::class
}

val useCaseModule = module {
    factoryOf(constructor = ::LoginUseCase)
    factoryOf(constructor = ::ValidateLoginUseCase)
    factoryOf(constructor = ::ClearAndInsertUserUseCase)
    factoryOf(constructor = ::GetFirstUserUseCase)
    factoryOf(constructor = ::SaveUserTokenUseCase)
}

val viewmodelModule = module {
    viewModelOf(constructor = ::LoginViewModel)
    viewModelOf(constructor = ::DashboardViewModel)
}

fun initKoin(config: KoinAppDeclaration? = null){
    startKoin {
        config?.invoke(this)
        modules(
            dispatcherModule,
            databaseModule,
            dataStoreModule,
            networkModule,
            dataSourceRemoteModule,
            dataSourceLocalModule,
            repositoryModule,
            useCaseModule,
            viewmodelModule,
        )
    }
}
