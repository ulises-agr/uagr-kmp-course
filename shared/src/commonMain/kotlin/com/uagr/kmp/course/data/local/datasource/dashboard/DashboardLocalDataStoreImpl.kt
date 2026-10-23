package com.uagr.kmp.course.data.local.datasource.dashboard

import com.uagr.kmp.course.data.local.database.dao.dashboard.DashboardDao
import com.uagr.kmp.course.domain.model.dashboard.DashboardModel

class DashboardLocalDataStoreImpl(
    private val dashboardDao: DashboardDao
): DashboardLocalDataStore {
    override suspend fun insertUDashboardAndDelete(dashboardModel: DashboardModel) {
       // dashboardDao.insertDashboardEntityAndDelete(dashboardModel.toEntity())
    }

    override suspend fun getDashboard(): DashboardModel? {
       return null
    }
}
