/*
 * PackagesModel.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.domain.model

data class PackagesModel(
    val data: List<PackagesDataModel>,
    val success: Boolean,
    val message: String,
)
