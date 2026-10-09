/*
 * TransactionsResponse.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.network.model.response.transaction

import com.uagr.kmp.course.data.network.model.response.base.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class TransactionsResponse(
    val items: List<TransactionResponse>? = null,
    val pagination: PaginationResponse? = null,
) : BaseResponse()
