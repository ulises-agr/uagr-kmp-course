/*
 * CoreModule.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.uagr.kmp.course.data.local.database.AppDatabase
import com.uagr.kmp.course.data.local.database.dao.register.UserDao
import com.uagr.kmp.course.data.local.database.dao.transactions.TransactionsDao
import com.uagr.kmp.course.data.local.database.getDatabaseBuilder
import com.uagr.kmp.course.data.local.datasource.bone.LoginNetworkDataSource
import com.uagr.kmp.course.data.local.datasource.login.RegisterLocalDataSource
import com.uagr.kmp.course.data.local.datasource.login.TokenLocalDataStore
import com.uagr.kmp.course.data.local.datasource.login.TokenLocalDataStoreImpl
import com.uagr.kmp.course.data.local.datasource.register.RegisterLocalDataSourceImpl
import com.uagr.kmp.course.data.local.datasource.transactions.TransactionsLocalDataSource
import com.uagr.kmp.course.data.local.datasource.transactions.TransactionsLocalDataSourceImpl
import com.uagr.kmp.course.data.network.datasource.login.LoginNetworkDataSourceImpl
import com.uagr.kmp.course.data.local.datastore.AppDataStore
import com.uagr.kmp.course.data.local.datastore.createDataStore
import com.uagr.kmp.course.data.network.client.createHttpClient
import com.uagr.kmp.course.data.network.datasource.budget.BudgetNetworkDataSource
import com.uagr.kmp.course.data.network.datasource.budget.BudgetNetworkDataSourceImpl
import com.uagr.kmp.course.data.network.datasource.dashboard.DashboardNetworkDataSource
import com.uagr.kmp.course.data.network.datasource.dashboard.DashboardNetworkDataSourceImpl
import com.uagr.kmp.course.data.network.datasource.register.RegisterNetworkDataSource
import com.uagr.kmp.course.data.network.datasource.register.RegisterNetworkDataSourceImpl
import com.uagr.kmp.course.data.network.datasource.transactions.TransactionsNetworkDataSource
import com.uagr.kmp.course.data.network.datasource.transactions.TransactionsNetworkDataSourceImpl
import com.uagr.kmp.course.data.repository.login.TokenRepositoryImpl
import com.uagr.kmp.course.data.repository.bone.LoginRepositoryImpl
import com.uagr.kmp.course.data.repository.budget.BudgetReposirotyImpl
import com.uagr.kmp.course.data.repository.dashboard.DashboardRepositoryImpl
import com.uagr.kmp.course.data.repository.register.RegisterRepositoryImpl
import com.uagr.kmp.course.data.repository.transactions.TransactionsRepositoryImpl
import com.uagr.kmp.course.domain.repository.TokenRepository.TokenRepository
import com.uagr.kmp.course.domain.repository.bone.LoginRepository
import com.uagr.kmp.course.domain.repository.budget.BudgetRepository
//import com.uagr.kmp.course.domain.repository.categories.CategoriesRepository
import com.uagr.kmp.course.domain.repository.dashboard.DashBoardRepository
import com.uagr.kmp.course.domain.repository.repository.RegisterRepository
import com.uagr.kmp.course.domain.repository.transactions.TransactionsRepository
import com.uagr.kmp.course.domain.usecase.dashboard.GetDashboardUseCase
//import com.uagr.kmp.course.domain.usecase.goals.GetBugetsUseCase
import com.uagr.kmp.course.domain.usecase.login.ValidateLoginFormUseCase
import com.uagr.kmp.course.domain.usecase.user.LoginUseCase
import com.uagr.kmp.course.domain.usecase.user.SaveTokenUseCase
import com.uagr.kmp.course.domain.usecase.register.RegisterUseCase
import com.uagr.kmp.course.domain.usecase.register.ValidateRegisterFormUseCase
//import com.uagr.kmp.course.domain.usecase.transactions.GetCategoriesUseCase
import com.uagr.kmp.course.domain.usecase.transactions.GetTransactionsUseCase
import com.uagr.kmp.course.domain.usecase.user.GetUserCase
import com.uagr.kmp.course.domain.usecase.user.GetUserTokenCase
import com.uagr.kmp.course.domain.usecase.user.SaveUserUseCase
import com.uagr.kmp.course.presentation.ui.buget.viewModel.BudgetViewModel
import com.uagr.kmp.course.presentation.ui.home.viewmodel.HomeViewModel
import com.uagr.kmp.course.presentation.ui.login.viewmodel.LoginViewModel
import com.uagr.kmp.course.presentation.ui.register.viewmodel.RegisterViewModel
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
    single<UserDao>{get <AppDatabase>().userDao()}
    single<TransactionsDao>{get <AppDatabase>().transactionsDao()}
}

val networkModule = module {
    single { createHttpClient(appDataStore = get()) }
}

val dataSourceRemoteModule = module {
    singleOf(constructor = ::LoginNetworkDataSourceImpl) bind LoginNetworkDataSource::class
    singleOf(constructor = ::RegisterNetworkDataSourceImpl) bind RegisterNetworkDataSource::class
    singleOf(constructor = ::TransactionsNetworkDataSourceImpl) bind TransactionsNetworkDataSource::class
    singleOf(constructor = ::DashboardNetworkDataSourceImpl) bind DashboardNetworkDataSource::class
    //singleOf(constructor = ::CategoriesNetworkDataSourceImpl) bind CategoriesNetworkDataSource::class
    //singleOf(constructor = ::BudgetNetworkDataSourceImpl) bind BudgetNetworkDataSource::class
}

val dataSourceLocalModule = module {
    singleOf(constructor = ::TokenLocalDataStoreImpl) bind TokenLocalDataStore::class
    singleOf(constructor = ::RegisterLocalDataSourceImpl) bind RegisterLocalDataSource::class
    singleOf(constructor = ::TransactionsLocalDataSourceImpl) bind TransactionsLocalDataSource::class
}

val repositoryModule = module {
    singleOf(constructor = ::LoginRepositoryImpl) bind LoginRepository::class
    singleOf(constructor = ::TokenRepositoryImpl) bind TokenRepository::class
    singleOf(constructor = ::RegisterRepositoryImpl) bind RegisterRepository::class
    singleOf(constructor = ::TransactionsRepositoryImpl) bind TransactionsRepository::class
    singleOf(constructor = ::DashboardRepositoryImpl) bind DashBoardRepository::class
   // singleOf(constructor = ::CategoriesRepositoryImpl) bind CategoriesRepository::class
   // singleOf(constructor = ::BudgetReposirotyImpl) bind BudgetRepository::class
}

val useCaseModule = module {
    factoryOf(constructor = ::LoginUseCase)
    factoryOf(constructor = ::ValidateLoginFormUseCase)
    factoryOf(constructor = ::SaveTokenUseCase)
    factoryOf(constructor = ::RegisterUseCase)
    factoryOf(constructor = ::SaveUserUseCase)
    factoryOf(constructor = ::GetUserCase)
    factoryOf(constructor = ::GetTransactionsUseCase)
    factoryOf(constructor = ::ValidateRegisterFormUseCase)
    factoryOf(constructor = ::GetDashboardUseCase)
    factoryOf(constructor = ::GetUserTokenCase)
    //factoryOf(constructor = ::GetCategoriesUseCase)
    //factoryOf(constructor = ::GetBugetsUseCase)
}

val viewmodelModule = module {
    viewModelOf(constructor = ::LoginViewModel)
    viewModelOf(constructor = ::RegisterViewModel)
    viewModelOf(constructor = ::HomeViewModel)
   // viewModelOf(constructor = ::BudgetViewModel)
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
