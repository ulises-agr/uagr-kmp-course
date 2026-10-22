/*
// UserRepository.kt
// Created by Karla Judith Cervantes Higareda on 08/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.domain.repository.dashboard

import com.uagr.kmp.course.domain.model.dashboard.DashboardModel
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {
    fun getDashboard(url: String): Flow<NetworkResult<DashboardModel>>
}
