/*
 * CoreModule.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.di

import com.uagr.kmp.course.data.local.database.AppDatabase
import com.uagr.kmp.course.data.local.database.dao.PackagesDao
import com.uagr.kmp.course.data.local.database.getDatabaseBuilder
import com.uagr.kmp.course.data.local.datasource.PackagesLocalDataSource
import com.uagr.kmp.course.data.local.datasource.PackagesLocalDataSourceImpl
import com.uagr.kmp.course.data.network.client.createHttpClient
import com.uagr.kmp.course.data.network.datasource.PackagesNetworkDataSource
import com.uagr.kmp.course.data.network.datasource.PackagesNetworkDataSourceImpl
import com.uagr.kmp.course.data.repository.PackagesRepositoryImpl
import com.uagr.kmp.course.domain.repository.PackagesRepository
import com.uagr.kmp.course.domain.usecase.ClearAndInsertPackagesUseCase
import com.uagr.kmp.course.domain.usecase.GetLocalPackagesUseCase
import com.uagr.kmp.course.domain.usecase.GetNetworkPackagesUseCase
import com.uagr.kmp.course.presentation.ui.packages.viewmodel.PackagesViewModel
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
    single<PackagesDao> { get<AppDatabase>().packagesDao() }
}

val networkModule = module {
    single { createHttpClient() }
}

val dataSourceRemoteModule = module {
    singleOf(constructor = ::PackagesNetworkDataSourceImpl) bind PackagesNetworkDataSource::class
}

val dataSourceLocalModule = module {
    singleOf(constructor = ::PackagesLocalDataSourceImpl) bind PackagesLocalDataSource::class
}

val repositoryModule = module {
    singleOf(constructor = ::PackagesRepositoryImpl) bind PackagesRepository::class
}

val useCaseModule = module {
    factoryOf(constructor = ::GetNetworkPackagesUseCase)
    factoryOf(constructor = ::ClearAndInsertPackagesUseCase)
    factoryOf(constructor = ::GetLocalPackagesUseCase)
}

val viewmodelModule = module {
    viewModelOf(constructor = ::PackagesViewModel)
}

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            dispatcherModule,
            databaseModule,
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
