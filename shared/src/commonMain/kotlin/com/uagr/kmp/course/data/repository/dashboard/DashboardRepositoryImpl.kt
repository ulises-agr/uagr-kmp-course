/*
// DashboardRepositoryImpl.kt
// Created by Karla Judith Cervantes Higareda on 08/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.data.repository.dashboard

import com.uagr.kmp.course.data.network.datasource.dashboard.DashboardNetworkDataSource
import com.uagr.kmp.course.domain.model.dashboard.DashboardModel
import com.uagr.kmp.course.domain.repository.dashboard.DashboardRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory

@Factory
class DashboardRepositoryImpl(
    private val dashboardNetworkDataSource: DashboardNetworkDataSource,
    private val ioDispatcher: CoroutineDispatcher,
) : DashboardRepository {
    override fun getDashboard(
        url: String
    ): Flow<NetworkResult<DashboardModel>> = flow {
        emit(
            dashboardNetworkDataSource.getDashboard(
            url = url
            )
        )
    }.flowOn(context = ioDispatcher)
}
