/*
 * PackagesMapper.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.mapper.packages

import com.uagr.kmp.course.data.local.model.packages.PackagesEntity
import com.uagr.kmp.course.data.network.model.response.packages.PackagesResponse
import com.uagr.kmp.course.domain.model.packages.PackagesModel

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

fun PackagesEntity.toDomain(): PackagesModel =
    PackagesModel(
        title = title.orEmpty(),
        packageDescription = packageDescription.orEmpty(),
        usedAmount = (usedAmount ?: 0.00).toString(),
        unit = unit.orEmpty(),
        renewalText = renewalText.orEmpty(),
        buttonText = buttonText.orEmpty(),
        hasInfoIcon = hasInfoIcon ?: false,
    )

fun PackagesModel.toEntity(): PackagesEntity =
    PackagesEntity(
        title = title,
        packageDescription = packageDescription,
        usedAmount = usedAmount,
        unit = unit,
        renewalText = renewalText,
        buttonText = buttonText,
        hasInfoIcon = hasInfoIcon,
    )
