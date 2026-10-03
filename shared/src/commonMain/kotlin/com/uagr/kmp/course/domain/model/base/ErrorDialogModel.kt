/*
// ErrorDialogModel.kt
// Created by Karla Judith Cervantes Higareda on 29/09/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.domain.model.base

data class ErrorDialogModel(
    val title: String = "",
    val message: String = "",
    val primaryButtonText: String = "",
    val secondaryButtonText: String = "",
)
