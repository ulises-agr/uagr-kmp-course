package com.uagr.kmp.course.domain.usecase.dashboard

import com.uagr.kmp.course.domain.model.dashboard.DashboardModel
import com.uagr.kmp.course.domain.repository.dashboard.DashBoardRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetDashboardUseCase (
    private val dashBoardRepository: DashBoardRepository,
){
    suspend fun callService(
        url: String,
        access_token: String,
    ): Flow<NetworkResult<DashboardModel>> =
        dashBoardRepository.callService(
            url = url,
            access_token = access_token
        )
}