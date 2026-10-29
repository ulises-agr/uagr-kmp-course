/*
 * PackagesUtils.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.util

import com.uagr.packages.sdk.domain.model.PackagesDataModel

fun filterActivePackages(packages: List<PackagesDataModel>): List<PackagesDataModel> =
    packages.filter { item -> item.stock.toIntOrNull()?.let { stock -> stock > 0 } ?: false }
