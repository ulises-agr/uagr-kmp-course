/*
 * CoreModule.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.uagr.kmp.course.data.local.database.AppDatabase
import com.uagr.kmp.course.data.local.database.dao.packages.PackagesDao
import com.uagr.kmp.course.data.local.database.dao.user.UserDao
import com.uagr.kmp.course.data.local.database.getDatabaseBuilder
import com.uagr.kmp.course.data.local.datasource.packages.PackagesLocalDataSource
import com.uagr.kmp.course.data.local.datasource.packages.PackagesLocalDataSourceImpl
import com.uagr.kmp.course.data.local.datasource.user.UserLocalDataSource
import com.uagr.kmp.course.data.local.datasource.user.UserLocalDataSourceImpl
import com.uagr.kmp.course.data.local.datastore.AppDataStore
import com.uagr.kmp.course.data.local.datastore.createDataStore
import com.uagr.kmp.course.data.network.client.createHttpClient
import com.uagr.kmp.course.data.network.datasource.login.LoginNetworkDataSource
import com.uagr.kmp.course.data.network.datasource.login.LoginNetworkDataSourceImpl
import com.uagr.kmp.course.data.network.datasource.packages.PackagesNetworkDataSource
import com.uagr.kmp.course.data.network.datasource.packages.PackagesNetworkDataSourceImpl
import com.uagr.kmp.course.data.repository.login.LoginRepositoryImpl
import com.uagr.kmp.course.data.repository.packages.PackagesRepositoryImpl
import com.uagr.kmp.course.data.repository.user.UserRepositoryImpl
import com.uagr.kmp.course.domain.repository.login.LoginRepository
import com.uagr.kmp.course.domain.repository.packages.PackagesRepository
import com.uagr.kmp.course.domain.repository.user.UserRepository
import com.uagr.kmp.course.domain.usecase.login.LoginUseCase
import com.uagr.kmp.course.domain.usecase.login.ValidateLoginFormUseCase
import com.uagr.kmp.course.domain.usecase.packages.ClearAndInsertPackagesUseCase
import com.uagr.kmp.course.domain.usecase.packages.GetLocalPackagesUseCase
import com.uagr.kmp.course.domain.usecase.packages.GetNetworkPackagesUseCase
import com.uagr.kmp.course.domain.usecase.user.InsertUserAndDeleteUseCase
import com.uagr.kmp.course.domain.usecase.user.SaveUserTokenUseCase
import com.uagr.kmp.course.presentation.ui.login.viewmodel.LoginViewModel
import com.uagr.kmp.course.presentation.ui.packages.viewmodel.PackagesViewModel
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
    single<PackagesDao> { get<AppDatabase>().packagesDao() }
    single<UserDao> { get<AppDatabase>().userDao() }
}

val networkModule = module {
    single { createHttpClient(appDataStore = get()) }
}

val dataSourceRemoteModule = module {
    singleOf(constructor = ::PackagesNetworkDataSourceImpl) bind PackagesNetworkDataSource::class
    singleOf(constructor = ::LoginNetworkDataSourceImpl) bind LoginNetworkDataSource::class
}

val dataSourceLocalModule = module {
    singleOf(constructor = ::PackagesLocalDataSourceImpl) bind PackagesLocalDataSource::class
    singleOf(constructor = ::UserLocalDataSourceImpl) bind UserLocalDataSource::class
}

val repositoryModule = module {
    singleOf(constructor = ::PackagesRepositoryImpl) bind PackagesRepository::class
    singleOf(constructor = ::UserRepositoryImpl) bind UserRepository::class
    singleOf(constructor = ::LoginRepositoryImpl) bind LoginRepository::class
}

val useCaseModule = module {
    factoryOf(constructor = ::GetNetworkPackagesUseCase)
    factoryOf(constructor = ::ClearAndInsertPackagesUseCase)
    factoryOf(constructor = ::GetLocalPackagesUseCase)
    factoryOf(constructor = ::LoginUseCase)
    factoryOf(constructor = ::SaveUserTokenUseCase)
    factoryOf(constructor = ::InsertUserAndDeleteUseCase)
    factoryOf(constructor = ::ValidateLoginFormUseCase)
}

val viewmodelModule = module {
    viewModelOf(constructor = ::PackagesViewModel)
    viewModelOf(constructor = ::WelcomeViewModel)
    viewModelOf(constructor = ::LoginViewModel)
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
