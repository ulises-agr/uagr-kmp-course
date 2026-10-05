/*
 * BudgetMapper.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.domain.mapper.budget

import com.uagr.kmp.course.data.network.model.response.budget.BudgetDataResponse
import com.uagr.kmp.course.data.network.model.response.budget.BudgetResponse
import com.uagr.kmp.course.domain.model.budget.BudgetDataModel
import com.uagr.kmp.course.domain.model.budget.BudgetModel

fun BudgetResponse.toDomain(): BudgetModel =
    BudgetModel(
        success = success ?: false,
        message = message.orEmpty(),
        data = data?.toDomain(),
    )

private fun BudgetDataResponse.toDomain(): BudgetDataModel =
    BudgetDataModel(
        title = title.orEmpty(),
        description = description.orEmpty(),
    )
