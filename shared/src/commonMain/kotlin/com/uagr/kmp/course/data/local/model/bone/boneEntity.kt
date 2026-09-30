package com.uagr.kmp.course.data.local.model.bone

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "bone")
data class BoneEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String?,
    val email: String?,
    val phone: String?,
    val role: String?,
)