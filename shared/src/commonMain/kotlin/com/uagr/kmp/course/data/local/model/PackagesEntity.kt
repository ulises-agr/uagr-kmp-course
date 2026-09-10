/*
 * PackagesEntity.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "packages")
data class PackagesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String?,
    val packageDescription: String?,
    val usedAmount: String?,
    val unit: String?,
    val renewalText: String?,
    val buttonText: String?,
    val hasInfoIcon: Boolean?,
)
