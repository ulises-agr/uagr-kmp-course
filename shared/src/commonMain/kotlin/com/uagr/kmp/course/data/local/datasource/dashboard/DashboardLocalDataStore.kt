package com.uagr.kmp.course.data.local.datasource.dashboard

import com.uagr.kmp.course.domain.model.dashboard.DashboardModel

interface DashboardLocalDataStore {
    suspend fun insertUDashboardAndDelete(dashboardModel: DashboardModel)
    suspend fun getDashboard(): DashboardModel?
}