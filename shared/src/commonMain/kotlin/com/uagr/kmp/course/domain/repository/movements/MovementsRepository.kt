/*
 * MovementsRepository.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.domain.repository.movements

import com.uagr.kmp.course.data.network.model.request.movements.MovementsRequest
import com.uagr.kmp.course.domain.model.movements.MovementsModel
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MovementsRepository {
    suspend fun fetchMovementsData(
        url: String,
        request: MovementsRequest,
    ): Flow<NetworkResult<MovementsModel>>
}
