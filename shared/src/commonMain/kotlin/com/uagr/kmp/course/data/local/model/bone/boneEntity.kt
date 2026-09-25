package com.uagr.kmp.course.data.local.model.bone

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bone")
data class BoneEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String?,
    val email: String?,
    val phone: String?,
    val role: String?,
)