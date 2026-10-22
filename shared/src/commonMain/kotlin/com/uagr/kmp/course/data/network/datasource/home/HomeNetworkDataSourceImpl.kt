package com.uagr.kmp.course.data.network.datasource.home

import com.uagr.kmp.course.data.network.model.response.home.DashboardResponse
import com.uagr.kmp.course.domain.mapper.home.toDomain
import com.uagr.kmp.course.domain.model.home.DashboardModel
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.network.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import org.koin.core.annotation.Factory

@Factory
class HomeNetworkDataSourceImpl(
    private val httpClient: HttpClient,
) : HomeNetworkDataSource {

    override suspend fun getDashboard(url: String): NetworkResult<DashboardModel> {
        return safeApiCall<DashboardResponse, DashboardModel>(
            apiCall = {
                httpClient.get(urlString = url)
            },
            transform = { response ->
                response.toDomain()
            },
        )
    }
}