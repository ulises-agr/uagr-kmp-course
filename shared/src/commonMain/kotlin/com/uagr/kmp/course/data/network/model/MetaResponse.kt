package com.uagr.kmp.course.data.network.model

import kotlinx.serialization.Serializable

@Serializable
open class MetaResponse(
    val page : Int? = 0,
    val per_page : Int? = 0,
    val total : Int? = 0,
    val total_pages : Int? = 0,
)