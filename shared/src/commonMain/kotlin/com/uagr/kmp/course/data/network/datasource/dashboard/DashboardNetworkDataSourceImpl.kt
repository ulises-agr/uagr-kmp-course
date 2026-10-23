package com.uagr.kmp.course.data.network.datasource.dashboard

import com.uagr.kmp.course.data.local.datastore.AppDataStore
import com.uagr.kmp.course.data.network.model.response.dashboard.DashboardResponse
import com.uagr.kmp.course.domain.mapper.dashboard.toDomain
import com.uagr.kmp.course.domain.model.dashboard.DashboardModel
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.network.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.first

class DashboardNetworkDataSourceImpl (
    private val httpClient: HttpClient,
):DashboardNetworkDataSource {
    override suspend fun getDashboard(
        url: String,
        access_token: String,
    ): NetworkResult<DashboardModel> =
        safeApiCall(
            apiCall = {
                httpClient.get(url) {
                    contentType(ContentType.Application.Json)
                    header("Authorization", "Bearer $access_token")
                    parameter("page", 1)
                    parameter("page_size", 20)
                }
            },
            transform = { data: DashboardResponse ->
                data.toDomain()
            },
        )

}