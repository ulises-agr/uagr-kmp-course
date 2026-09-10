/*
 * PackagesDao.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.local.datasource

import com.uagr.kmp.course.data.local.database.dao.PackagesDao
import com.uagr.kmp.course.domain.mapper.toDomain
import com.uagr.kmp.course.domain.mapper.toEntity
import com.uagr.kmp.course.domain.model.PackagesModel

class PackagesLocalDataSourceImpl(
    private val packagesDao: PackagesDao
) : PackagesLocalDataSource {

    override suspend fun clearAndInsertPackages(packages: PackagesModel) {
        packagesDao.clearAndInsertPackages(packages = packages.toEntity())
    }

    override suspend fun getPackages(): PackagesModel? =
        packagesDao.getPackages()?.toDomain()
}
