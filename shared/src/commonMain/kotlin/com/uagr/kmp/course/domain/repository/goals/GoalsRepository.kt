/*
 * GoalsRepository.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.domain.repository.goals

import com.uagr.kmp.course.data.network.model.request.goals.GoalsRequest
import com.uagr.kmp.course.domain.model.goals.GoalsModel
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface GoalsRepository {
    suspend fun fetchGoalsData(
        url: String,
        request: GoalsRequest,
    ): Flow<NetworkResult<GoalsModel>>
}
