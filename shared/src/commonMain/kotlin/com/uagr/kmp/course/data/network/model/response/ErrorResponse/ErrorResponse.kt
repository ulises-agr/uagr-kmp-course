package com.uagr.kmp.course.data.network.model.response.ErrorResponse

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse (
    val error: ErrorResult
)
