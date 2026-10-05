package com.uagr.kmp.course.domain.usecase.goals

import com.uagr.kmp.course.data.network.model.request.goals.GoalsRequest
import com.uagr.kmp.course.domain.model.goals.GoalsModel
import com.uagr.kmp.course.domain.repository.goals.GoalsRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class GoalsUseCase(
    private val repository: GoalsRepository,
) {
    suspend fun fetchGoalsData(
        url: String,
        query: String = "",
    ): Flow<NetworkResult<GoalsModel>> =
        repository.fetchGoalsData(
            url = url,
            request = GoalsRequest(query = query),
        )
}
