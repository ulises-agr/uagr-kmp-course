/*
 * PackagesMapper.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.domain.mapper

import com.uagr.packages.sdk.data.local.entity.PackagesEntity
import com.uagr.packages.sdk.data.network.model.PackagesDataResponse
import com.uagr.packages.sdk.data.network.model.PackagesResponse
import com.uagr.packages.sdk.domain.model.PackagesDataModel
import com.uagr.packages.sdk.domain.model.PackagesModel

fun PackagesResponse.toDomain(): PackagesModel =
    PackagesModel(
        data = data?.map { item -> item.toDomain() } ?: emptyList(),
        success = success ?: false,
        message = message.orEmpty(),
    )

private fun PackagesDataResponse.toDomain(): PackagesDataModel =
    PackagesDataModel(
        id = id.orEmpty(),
        name = name.orEmpty(),
        description = description.orEmpty(),
        price = (price ?: 0.00).toString(),
        currency = currency.orEmpty(),
        stock = (stock ?: 0).toString(),
        created_by = created_by.orEmpty(),
        created_at = created_at.orEmpty(),
    )

fun PackagesDataModel.toEntity(): PackagesEntity =
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

fun PackagesEntity.toDomain(): PackagesDataModel =
    PackagesDataModel(
        id = id,
        name = name,
        description = description,
        price = price,
        currency = currency,
        stock = stock,
        created_by = created_by,
        created_at = created_at,
    )
