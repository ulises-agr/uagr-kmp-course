package com.uagr.kmp.course.domain.repository.dashboard


import com.uagr.kmp.course.domain.model.dashboard.DashboardModel
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface DashBoardRepository {
    suspend fun callService(
        url: String,
        access_token: String
    ): Flow<NetworkResult<DashboardModel>>
}