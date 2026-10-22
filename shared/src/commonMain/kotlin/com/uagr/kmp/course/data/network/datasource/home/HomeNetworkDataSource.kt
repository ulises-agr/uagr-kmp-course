package com.uagr.kmp.course.data.network.datasource.home

import com.uagr.kmp.course.domain.model.home.DashboardModel
import com.uagr.kmp.course.utils.network.NetworkResult

interface HomeNetworkDataSource {
    suspend fun getDashboard(url: String): NetworkResult<DashboardModel>
}