package com.uagr.kmp.course.domain.model.goals

data class GoalsModel(
    val success: Boolean = false,
    val message: String = "",
    val data: GoalsDataModel? = null,
)
