/*
 * HomeRepository.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.domain.repository.home

import com.uagr.kmp.course.data.network.model.request.home.HomeRequest
import com.uagr.kmp.course.domain.model.home.HomeModel
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun fetchHomeData(
        url: String,
        request: HomeRequest,
    ): Flow<NetworkResult<HomeModel>>
}
