/*
// DashboardGoalResponse.kt
// Created by Karla Judith Cervantes Higareda on 09/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.data.network.model.response.dashboard

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DashboardGoalResponse(
    val id: String?,
    val name: String?,
    @SerialName("target_amount") val targetAmount: String?,
    @SerialName("current_amount") val currentAmount: String?,
    @SerialName("target_date") val targetDate: String?,
    val status: String?,
    @SerialName("created_at") val createdAt: String?,
    @SerialName("updated_at") val updatedAt: String?,
    val version: Int?,
)