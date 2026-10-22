/*
// DashboardGoalModel.kt
// Created by Karla Judith Cervantes Higareda on 09/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.domain.model.dashboard

data class DashboardGoalModel(
    val id: String,
    val name: String,
    val targetAmount: String,
    val currentAmount: String,
    val targetDate: String,
    val status: String,
    val createdAt: String,
    val updatedAt: String,
    val version: Int,
)