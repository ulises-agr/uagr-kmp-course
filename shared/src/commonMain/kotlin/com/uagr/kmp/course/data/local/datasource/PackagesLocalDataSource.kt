/*
 * PackagesLocalDataSource.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.local.datasource

import com.uagr.kmp.course.domain.model.PackagesModel

interface PackagesLocalDataSource {
    suspend fun clearAndInsertPackages(packages: PackagesModel)
    suspend fun getPackages(): PackagesModel?
}
