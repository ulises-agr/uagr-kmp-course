/*
// DashboardTransactionResponse.kt
// Created by Karla Judith Cervantes Higareda on 09/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.data.network.model.response.dashboard

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DashboardTransactionResponse(
    val id: String?,
    @SerialName("account_id") val accountId: String?,
    @SerialName("category_id") val categoryId: String?,
    val type: String?,
    val amount: String?,
    val currency: String?,
    val description: String?,
    val notes: String?,
    @SerialName("transaction_date") val transactionDate: String?,
    @SerialName("created_at") val createdAt: String?,
    @SerialName("updated_at") val updatedAt: String?,
    val version: Int?,
)