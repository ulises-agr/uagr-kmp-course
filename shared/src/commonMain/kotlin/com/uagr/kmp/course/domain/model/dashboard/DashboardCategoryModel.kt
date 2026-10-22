/*
// DashboardCategoryModel.kt
// Created by Karla Judith Cervantes Higareda on 09/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.domain.model.dashboard

data class DashboardCategoryModel(
    val categoryId: String,
    val categoryName: String,
    val amount: String,
    val percentage: String,
)