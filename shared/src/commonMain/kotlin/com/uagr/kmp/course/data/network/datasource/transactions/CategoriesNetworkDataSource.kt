package com.uagr.kmp.course.data.network.datasource.transactions

import com.uagr.kmp.course.domain.model.transactions.CategoriesDataModel
import com.uagr.kmp.course.utils.network.NetworkResult

interface CategoriesNetworkDataSource {
    suspend fun getCategories(
        url: String,
        access_token: String,
    ): NetworkResult<List<CategoriesDataModel>>
}