/*
 * HomeMapper.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.domain.mapper.home

import com.uagr.kmp.course.data.network.model.response.home.HomeDataResponse
import com.uagr.kmp.course.data.network.model.response.home.HomeResponse
import com.uagr.kmp.course.domain.model.home.HomeDataModel
import com.uagr.kmp.course.domain.model.home.HomeModel

fun HomeResponse.toDomain(): HomeModel =
    HomeModel(
        success = success ?: false,
        message = message.orEmpty(),
        data = data?.toDomain(),
    )

private fun HomeDataResponse.toDomain(): HomeDataModel =
    HomeDataModel(
        title = title.orEmpty(),
        description = description.orEmpty(),
    )
