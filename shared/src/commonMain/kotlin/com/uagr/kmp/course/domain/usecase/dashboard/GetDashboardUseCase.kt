/*
// GetDashboardUseCase.kt
// Created by Karla Judith Cervantes Higareda on 08/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.domain.usecase.dashboard

import com.uagr.kmp.course.domain.model.dashboard.DashboardModel
import com.uagr.kmp.course.domain.repository.dashboard.DashboardRepository
import com.uagr.kmp.course.utils.constant.NetworkUrl
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class GetDashboardUseCase(
    private val dashboardRepository: DashboardRepository,
) {
    operator fun invoke(): Flow<NetworkResult<DashboardModel>> =
        dashboardRepository.getDashboard(url = NetworkUrl.DASHBOARD_ENDPOINT)
}
