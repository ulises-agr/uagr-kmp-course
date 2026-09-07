/*
 * PackagesMapper.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.mapper

import com.uagr.kmp.course.data.network.model.response.PackagesResponse
import com.uagr.kmp.course.domain.model.PackagesModel

fun PackagesResponse.toDomain(): PackagesModel =
    PackagesModel(
        title = title.orEmpty(),
        packageDescription = packageDescription.orEmpty(),
        usedAmount = (usedAmount ?: 0.00).toString(),
        unit = unit.orEmpty(),
        renewalText = renewalText.orEmpty(),
        buttonText = buttonText.orEmpty(),
        hasInfoIcon = hasInfoIcon ?: false,
    )
