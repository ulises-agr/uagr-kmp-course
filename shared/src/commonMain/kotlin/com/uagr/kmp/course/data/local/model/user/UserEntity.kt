/*
 * UserEntity.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.local.model.user

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String?,
    val email: String?,
    val phone: String?,
    val role: String?,
)
