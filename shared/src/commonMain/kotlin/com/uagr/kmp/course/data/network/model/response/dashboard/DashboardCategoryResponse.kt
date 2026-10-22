/*
// DashboardCategoryResponse.kt
// Created by Karla Judith Cervantes Higareda on 09/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.data.network.model.response.dashboard

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DashboardCategoryResponse(
    @SerialName("category_id") val categoryId: String?,
    @SerialName("category_name") val categoryName: String?,
    val amount: String?,
    val percentage: String?,
)
