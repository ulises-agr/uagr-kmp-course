/*
 * CoreModule.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.kmp.di

import com.kmp.presentation.welcome.viewmodel.WelcomeViewModel
import com.uagr.kmp.course.data.local.database.AppDatabase
import com.uagr.kmp.course.data.local.database.dao.PackageDAO
import com.uagr.kmp.course.data.local.database.getDatabaseBuilder
import com.uagr.kmp.course.data.local.datasource.PackagesLocalDataSource
import com.uagr.kmp.course.data.local.datasource.PackagesLocalDataSourceImp
import com.uagr.kmp.course.data.network.client.createHttpClient
import com.uagr.kmp.course.data.network.datasource.PackagesNetworkDataSource
import com.uagr.kmp.course.data.network.datasource.PackagesNetworkDataSourceImp
import com.uagr.kmp.course.domain.repository.PackageRepositoryImp
import com.uagr.kmp.course.domain.repository.PackagesRepository
import com.uagr.kmp.course.domain.usecase.ClearAndInsertPackagesUseCase
import com.uagr.kmp.course.domain.usecase.GetLocalPackagesUseCase
import com.uagr.kmp.course.domain.usecase.GetNetworkPackagesUseCase
import com.uagr.kmp.course.presentation.packages.viewmodel.PackageViewModel
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

val databaseDaoModule = module {
    single<PackageDAO> { get<AppDatabase>().packageDAO() }
}

val viewmodelModule = module {
    viewModelOf(constructor = ::WelcomeViewModel)
    viewModelOf(constructor = ::PackageViewModel)
}

val useCaseModule = module {
    factoryOf(constructor = ::GetNetworkPackagesUseCase)
    factoryOf(constructor = ::ClearAndInsertPackagesUseCase)
    factoryOf(constructor = ::GetLocalPackagesUseCase)
}

val dataSourceRemoteModule = module {
    singleOf(constructor = ::PackagesNetworkDataSourceImp) bind PackagesNetworkDataSource::class
}

val dataSourceLocalModule = module {
    singleOf(constructor = ::PackagesLocalDataSourceImp) bind PackagesLocalDataSource::class
}

val repositoryModule = module {
    singleOf(constructor = ::PackageRepositoryImp) bind PackagesRepository::class
}

val networkModule = module {
    single { createHttpClient() }
}

fun initKoin(config: KoinAppDeclaration? = null){
    startKoin {
        config?.invoke(this)
        modules(
            viewmodelModule,
            useCaseModule,
            databaseModule,
            databaseDaoModule,
            dataSourceRemoteModule,
            dataSourceLocalModule,
            repositoryModule,
            dispatcherModule,
            networkModule
        )
    }
}
