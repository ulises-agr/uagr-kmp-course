/*
 * MovementsMapper.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.domain.mapper.movements

import com.uagr.kmp.course.data.network.model.response.movements.MovementsDataResponse
import com.uagr.kmp.course.data.network.model.response.movements.MovementsResponse
import com.uagr.kmp.course.domain.model.movements.MovementsDataModel
import com.uagr.kmp.course.domain.model.movements.MovementsModel

fun MovementsResponse.toDomain(): MovementsModel =
    MovementsModel(
        success = success ?: false,
        message = message.orEmpty(),
        data = data?.toDomain(),
    )

private fun MovementsDataResponse.toDomain(): MovementsDataModel =
    MovementsDataModel(
        title = title.orEmpty(),
        description = description.orEmpty(),
    )
