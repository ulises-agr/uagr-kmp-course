package com.uagr.kmp.course.data.network.datasource.dashboard

import com.uagr.kmp.course.domain.model.dashboard.DashboardModel
import com.uagr.kmp.course.utils.network.NetworkResult

interface DashboardNetworkDataSource {
    suspend fun getDashboard(
        url: String,
        access_token: String,
    ): NetworkResult<DashboardModel>
}