package com.uagr.kmp.course.data.repository.dashboard

import com.uagr.kmp.course.data.local.datasource.dashboard.DashboardLocalDataStore
import com.uagr.kmp.course.data.network.datasource.dashboard.DashboardNetworkDataSource
import com.uagr.kmp.course.domain.model.dashboard.DashboardModel
import com.uagr.kmp.course.domain.repository.dashboard.DashBoardRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DashboardRepositoryImpl(
    private val dashboardNetworkDataSource: DashboardNetworkDataSource,
    private val dispatcher: CoroutineDispatcher,
): DashBoardRepository{
    override suspend fun callService(url: String, access_token: String): Flow<NetworkResult<DashboardModel>> = flow {
        emit(dashboardNetworkDataSource.getDashboard(
            url = url,
            access_token = access_token
        ))
    }.flowOn(dispatcher)

}

