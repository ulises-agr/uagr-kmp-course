package com.uagr.kmp.course.domain.repository.home

import com.uagr.kmp.course.domain.model.home.DashboardModel
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getDashboard(url: String): Flow<NetworkResult<DashboardModel>>
}