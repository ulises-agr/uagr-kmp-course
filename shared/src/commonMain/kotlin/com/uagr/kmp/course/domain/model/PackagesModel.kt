/*
 * PackagesModel.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.model

data class PackagesModel(
    val title: String,
    val packageDescription: String,
    val usedAmount: String,
    val unit: String,
    val renewalText: String,
    val buttonText: String,
    val hasInfoIcon: Boolean,
)
