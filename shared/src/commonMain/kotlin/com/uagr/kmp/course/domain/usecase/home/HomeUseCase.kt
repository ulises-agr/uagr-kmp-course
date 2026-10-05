package com.uagr.kmp.course.domain.usecase.home

import com.uagr.kmp.course.data.network.model.request.home.HomeRequest
import com.uagr.kmp.course.domain.model.home.HomeModel
import com.uagr.kmp.course.domain.repository.home.HomeRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class HomeUseCase(
    private val repository: HomeRepository,
) {
    suspend fun fetchHomeData(
        url: String,
        query: String = "",
    ): Flow<NetworkResult<HomeModel>> =
        repository.fetchHomeData(
            url = url,
            request = HomeRequest(query = query),
        )
}
