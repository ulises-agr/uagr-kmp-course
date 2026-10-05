/*
 * GoalsMapper.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.domain.mapper.goals

import com.uagr.kmp.course.data.network.model.response.goals.GoalsDataResponse
import com.uagr.kmp.course.data.network.model.response.goals.GoalsResponse
import com.uagr.kmp.course.domain.model.goals.GoalsDataModel
import com.uagr.kmp.course.domain.model.goals.GoalsModel

fun GoalsResponse.toDomain(): GoalsModel =
    GoalsModel(
        success = success ?: false,
        message = message.orEmpty(),
        data = data?.toDomain(),
    )

private fun GoalsDataResponse.toDomain(): GoalsDataModel =
    GoalsDataModel(
        title = title.orEmpty(),
        description = description.orEmpty(),
    )
