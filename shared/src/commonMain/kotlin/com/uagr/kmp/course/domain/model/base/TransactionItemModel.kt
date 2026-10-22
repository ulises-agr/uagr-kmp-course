/*
// TransactionItemModel.kt
// Created by Karla Judith Cervantes Higareda on 09/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.domain.model.base

import com.uagr.kmp.course.presentation.component.list.TransactionType

data class TransactionItemModel(
    val id: String?,
    val title: String,
    val subtitle: String,
    val amount: String,
    val type: TransactionType,
)
