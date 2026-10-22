package com.uagr.kmp.course.data.repository.home

import com.uagr.kmp.course.data.network.datasource.home.HomeNetworkDataSource
import com.uagr.kmp.course.domain.model.home.DashboardModel
import com.uagr.kmp.course.domain.repository.home.HomeRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory

@Factory
class HomeRepositoryImpl(
    private val homeNetworkDataSource: HomeNetworkDataSource,
    private val dispatcher: CoroutineDispatcher,
) : HomeRepository {

    override suspend fun getDashboard(
        url: String,
    ): Flow<NetworkResult<DashboardModel>> = flow {
        emit(
            homeNetworkDataSource.getDashboard(url = url)
        )
    }.flowOn(context = dispatcher)
}