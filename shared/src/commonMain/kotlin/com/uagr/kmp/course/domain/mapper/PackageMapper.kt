package com.uagr.kmp.course.domain.mapper

import com.uagr.kmp.course.data.local.model.PackagesEntity
import com.uagr.kmp.course.data.network.model.response.PackagesResponse
import com.uagr.kmp.course.domain.model.PackageModel

fun PackagesResponse.toDomain() : PackageModel =
    PackageModel(
        title = title.orEmpty(),
        packageDescription = packageDescription.orEmpty(),
        usedAmount = (usedAmount?:0.0).toString(),
        unit = unit.orEmpty(),
        renewalText = renewalText.orEmpty(),
        buttonText = buttonText.orEmpty(),
        hasInfoIcon = hasInfoIcon ?: false
    )

fun PackageModel.toEntity() : PackagesEntity =
    PackagesEntity(
        title = title.orEmpty(),
        packageDescription = packageDescription.orEmpty(),
        usedAmount = (usedAmount?:0.0).toString(),
        unit = unit.orEmpty(),
        renewalText = renewalText.orEmpty(),
        buttonText = buttonText.orEmpty(),
        hasInfoIcon = hasInfoIcon
    )


fun PackagesEntity.toDomain() : PackageModel =
    PackageModel(
        title = title.orEmpty(),
        packageDescription = packageDescription.orEmpty(),
        usedAmount = (usedAmount?:0.0).toString(),
        unit = unit.orEmpty(),
        renewalText = renewalText.orEmpty(),
        buttonText = buttonText.orEmpty(),
        hasInfoIcon = hasInfoIcon?:false
    )


