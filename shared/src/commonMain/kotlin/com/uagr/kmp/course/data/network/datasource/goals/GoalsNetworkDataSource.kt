/*
 * GoalsNetworkDataSource.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.network.datasource.goals

import com.uagr.kmp.course.data.network.model.request.goals.GoalsRequest
import com.uagr.kmp.course.domain.model.goals.GoalsModel
import com.uagr.kmp.course.utils.network.NetworkResult

interface GoalsNetworkDataSource {
    suspend fun fetchGoalsData(
        url: String,
        request: GoalsRequest,
    ): NetworkResult<GoalsModel>
}
