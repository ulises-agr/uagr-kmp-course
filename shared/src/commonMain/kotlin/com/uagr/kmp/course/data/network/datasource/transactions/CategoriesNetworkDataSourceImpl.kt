package com.uagr.kmp.course.data.network.datasource.transactions
/*
import com.uagr.kmp.course.data.network.model.response.transactions.CategoriesResult
import com.uagr.kmp.course.domain.model.transactions.CategoriesDataModel
import com.uagr.kmp.course.domain.mapper.transactions.toDomian
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.network.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType

class CategoriesNetworkDataSourceImpl(
    private val httpClient: HttpClient,
): CategoriesNetworkDataSource {
    override suspend fun getCategories(
        url: String,
        access_token: String
    ): NetworkResult<List<CategoriesDataModel>> =
        safeApiCall(
            apiCall = {
                httpClient.get(url) {
                    contentType(ContentType.Application.Json)
                    header("Authorization", "Bearer $access_token")
                }
            },
            transform = { data: List<CategoriesResult> ->
                data.map { it.toDomian() }
            },
        )
}*/