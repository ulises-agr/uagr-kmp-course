package com.uagr.kmp.course.domain.model.base

data class ErrorDialogModel(
    val title: String = "",
    val message: String = "",
    val primaryButtonText: String = "",
    val secondaryButtonText: String = "",
)