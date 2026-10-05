package com.uagr.kmp.course.domain.usecase.movements

import com.uagr.kmp.course.data.network.model.request.movements.MovementsRequest
import com.uagr.kmp.course.domain.model.movements.MovementsModel
import com.uagr.kmp.course.domain.repository.movements.MovementsRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class MovementsUseCase(
    private val repository: MovementsRepository,
) {
    suspend fun fetchMovementsData(
        url: String,
        query: String = "",
    ): Flow<NetworkResult<MovementsModel>> =
        repository.fetchMovementsData(
            url = url,
            request = MovementsRequest(query = query),
        )
}
