package com.uagr.kmp.course.domain.model.movements

data class MovementsModel(
    val success: Boolean = false,
    val message: String = "",
    val data: MovementsDataModel? = null,
)
