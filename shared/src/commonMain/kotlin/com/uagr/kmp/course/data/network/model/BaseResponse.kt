package com.uagr.kmp.course.data.network.model

import kotlinx.serialization.Serializable

@Serializable
open class BaseResponse(
    val success : Boolean? = false,
    val message : String? = "",
    val meta: Boolean? = false,
    val error: String? = ""
)