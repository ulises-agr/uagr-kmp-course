package com.uagr.kmp.course.domain.usecase.home

import com.uagr.kmp.course.domain.model.home.DashboardModel
import com.uagr.kmp.course.domain.repository.home.HomeRepository
import com.uagr.kmp.course.utils.constant.NetworkUrl
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class GetDashboardUseCase(
    private val homeRepository: HomeRepository,
) {
    suspend operator fun invoke(
        url: String = NetworkUrl.DASHBOARD_ENDPOINT,
    ): Flow<NetworkResult<DashboardModel>> =
        homeRepository.getDashboard(url = url)
}