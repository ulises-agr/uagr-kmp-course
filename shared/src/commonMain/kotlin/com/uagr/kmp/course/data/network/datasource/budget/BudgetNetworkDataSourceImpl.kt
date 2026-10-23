package com.uagr.kmp.course.data.network.datasource.budget

import com.uagr.kmp.course.data.network.model.response.budget.BudgetListResponse
import com.uagr.kmp.course.data.network.model.response.budget.BudgetsResponse
import com.uagr.kmp.course.domain.mapper.budget.toDomain
import com.uagr.kmp.course.domain.model.budget.BudgetListResultModel
import com.uagr.kmp.course.domain.model.budget.BudgetResumeModel
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.network.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType

class BudgetNetworkDataSourceImpl(
    private val httpClient: HttpClient,
): BudgetNetworkDataSource {
    override suspend fun getBudget(
        url: String,
        access_token: String,
    ): NetworkResult<BudgetResumeModel> =
        safeApiCall(
            apiCall = {
                httpClient.get(url) {
                    contentType(ContentType.Application.Json)
                    header("Authorization", "Bearer $access_token")
                }
            },
            transform = { data: BudgetsResponse ->
                data.toDomain()
            },
        )


    override suspend fun getListBuget(
        url: String,
        access_token: String,
        dateStart: String,
        dateEnd: String
    ): NetworkResult<BudgetListResultModel> =
        safeApiCall(
            apiCall = {
                httpClient.get(url) {
                    contentType(ContentType.Application.Json)
                    header("Authorization", "Bearer $access_token")
                    header("date_from",dateStart)
                    header("date_to",dateEnd)
                }
            },
            transform = { data: BudgetListResponse ->
                data.toDomain()
            },
        )
}