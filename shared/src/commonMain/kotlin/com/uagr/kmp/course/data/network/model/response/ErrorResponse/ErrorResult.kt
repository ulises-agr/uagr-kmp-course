package com.uagr.kmp.course.data.network.model.response.ErrorResponse

import kotlinx.serialization.Serializable
import org.koin.core.logger.MESSAGE

@Serializable
data class ErrorResult(
    val code: String,
    val message: String,
    val details: String? = null
){
}