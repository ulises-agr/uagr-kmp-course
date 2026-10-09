/*
 * DashboardRepository.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.domain.repository.dashboard

import com.uagr.kmp.course.domain.model.dashboard.DashboardModel
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {
    suspend fun getDashboard(
        url: String,
        token: String,
    ): Flow<NetworkResult<DashboardModel>>
}
