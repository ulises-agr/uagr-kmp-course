package com.uagr.kmp.course.data.network.model

import kotlinx.serialization.Serializable

@Serializable
open class BaseResponse(
    val codeServices : Int? = 0,
    val error: String? = "",
    val message : String? = "",
    val meta: MetaResponse? = MetaResponse(
        page = 0,
        per_page = 0,
        total = 0,
        total_pages = 0
    ),
    val success : Boolean? = false,
)