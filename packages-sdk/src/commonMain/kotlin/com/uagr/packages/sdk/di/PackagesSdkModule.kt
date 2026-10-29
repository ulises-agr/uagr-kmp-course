/*
 * PackagesSdkModule.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.di

import com.uagr.packages.sdk.config.PackagesSdkConfig
import com.uagr.packages.sdk.config.PackagesTokenProvider
import com.uagr.packages.sdk.data.local.database.PackagesDatabase
import com.uagr.packages.sdk.data.local.database.getPackagesDatabaseBuilder
import com.uagr.packages.sdk.data.local.datasource.PackagesLocalDataSource
import com.uagr.packages.sdk.data.local.datasource.PackagesLocalDataSourceImpl
import com.uagr.packages.sdk.data.network.client.createPackagesHttpClient
import com.uagr.packages.sdk.data.network.datasource.PackagesNetworkDataSource
import com.uagr.packages.sdk.data.network.datasource.PackagesNetworkDataSourceImpl
import com.uagr.packages.sdk.data.repository.PackagesRepositoryImpl
import com.uagr.packages.sdk.domain.repository.PackagesRepository
import com.uagr.packages.sdk.domain.usecase.ClearAndInsertPackagesUseCase
import com.uagr.packages.sdk.domain.usecase.GetLocalPackagesUseCase
import com.uagr.packages.sdk.domain.usecase.GetNetworkPackagesUseCase
import com.uagr.packages.sdk.presentation.PackagesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

private val PackagesSdkDispatcher = named("PackagesSdkDispatcher")
private val PackagesSdkHttpClient = named("PackagesSdkHttpClient")

/**
 * Requires the host to register a [PackagesTokenProvider] singleton
 * before or alongside this module.
 */
fun packagesSdkModule(
    baseUrl: String,
    productsEndpoint: String = PackagesSdkConfig.DEFAULT_PRODUCTS_ENDPOINT,
): Module = module {
    single {
        PackagesSdkConfig(
            baseUrl = baseUrl,
            productsEndpoint = productsEndpoint,
            tokenProvider = get(),
        )
    }

    single(qualifier = PackagesSdkDispatcher) { Dispatchers.IO }

    single(qualifier = PackagesSdkHttpClient) {
        createPackagesHttpClient(config = get())
    }

    single {
        getPackagesDatabaseBuilder()
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    single { get<PackagesDatabase>().packagesDao() }

    factory {
        PackagesNetworkDataSourceImpl(
            httpClient = get(qualifier = PackagesSdkHttpClient),
        )
    } bind PackagesNetworkDataSource::class

    factoryOf(::PackagesLocalDataSourceImpl) bind PackagesLocalDataSource::class

    factory {
        PackagesRepositoryImpl(
            packagesNetworkDataSource = get(),
            packagesLocalDataSource = get(),
            dispatcher = get(qualifier = PackagesSdkDispatcher),
        )
    } bind PackagesRepository::class

    factoryOf(::GetNetworkPackagesUseCase)
    factoryOf(::ClearAndInsertPackagesUseCase)
    factoryOf(::GetLocalPackagesUseCase)

    viewModel {
        PackagesViewModel(
            config = get(),
            getNetworkPackagesUseCase = get(),
            clearAndInsertPackagesUseCase = get(),
            getLocalPackagesUseCase = get(),
        )
    }
}
