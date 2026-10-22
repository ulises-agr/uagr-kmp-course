/*
// DashboardTransactionModel.kt
// Created by Karla Judith Cervantes Higareda on 09/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.domain.model.dashboard

data class DashboardTransactionModel(
    val id: String,
    val accountId: String,
    val categoryId: String,
    val type: String,
    val amount: String,
    val currency: String,
    val description: String,
    val notes: String,
    val transactionDate: String,
    val createdAt: String,
    val updatedAt: String,
    val version: Int,
)