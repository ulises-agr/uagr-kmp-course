package com.uagr.kmp.course.data.local.model.register

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String?,
    val email: String?,
    val currency: String?,
    val timezone: String?,
    val locale: String?,
    val is_active: Boolean?,
    val email_verified: Boolean?,
    val created_at: String?,
    val updated_at: String?,
)