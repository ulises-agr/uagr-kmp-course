package com.uagr.kmp.course.domain.mapper.packages

import com.uagr.kmp.course.data.local.model.PackagesEntity
import com.uagr.kmp.course.data.network.model.packages.response.PackagesDataResponse
import com.uagr.kmp.course.data.network.model.packages.response.PackagesResponse
import com.uagr.kmp.course.domain.model.packages.PackageDataModel
import com.uagr.kmp.course.domain.model.packages.PackageModel

fun PackagesResponse.toDomain(): PackageModel =
    PackageModel(
        data = data?.map { data -> data.toDomain() } ?: emptyList(),
        success = success ?: false,
        message = message.orEmpty(),
        error = error.orEmpty()
    )

private fun PackagesDataResponse.toDomain(): PackageDataModel =
    PackageDataModel(
        id = id.orEmpty(),
        name = name.orEmpty(),
        description = description.orEmpty(),
        price = (price ?: 0.00).toString(),
        currency = currency.orEmpty(),
        stock = (stock ?: 0).toString(),
        created_by = created_by.orEmpty(),
        created_at = created_at.orEmpty(),
    )

fun PackageDataModel.toEntity(): PackagesEntity =
    PackagesEntity(
        id = id,
        name = name,
        description = description,
        price = price,
        currency = currency,
        stock = stock,
        created_by = created_by,
        created_at = created_at,
    )

fun PackagesEntity.toDomain(): PackageDataModel =
    PackageDataModel(
        id = id,
        name = name,
        description = description,
        price = price,
        currency = currency,
        stock = stock,
        created_by = created_by,
        created_at = created_at,
    )


