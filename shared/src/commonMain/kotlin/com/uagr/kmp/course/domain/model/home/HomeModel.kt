package com.uagr.kmp.course.domain.model.home

data class HomeModel(
    val success: Boolean = false,
    val message: String = "",
    val data: HomeDataModel? = null,
)
