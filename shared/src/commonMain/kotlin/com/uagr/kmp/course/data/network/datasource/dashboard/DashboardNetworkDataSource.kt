/*
// DashboardNetworkDataSource.kt
// Created by Karla Judith Cervantes Higareda on 08/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.data.network.datasource.dashboard

import com.uagr.kmp.course.domain.model.dashboard.DashboardModel
import com.uagr.kmp.course.utils.network.NetworkResult

interface DashboardNetworkDataSource {
    suspend fun getDashboard(
        url: String
    ): NetworkResult<DashboardModel>
}
