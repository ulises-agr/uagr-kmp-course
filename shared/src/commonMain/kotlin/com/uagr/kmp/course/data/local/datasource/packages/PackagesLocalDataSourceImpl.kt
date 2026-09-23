/*
 * PackagesLocalDataSourceImpl.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.local.datasource.packages

import com.uagr.kmp.course.data.local.database.dao.packages.PackagesDao
import com.uagr.kmp.course.domain.mapper.packages.toDomain
import com.uagr.kmp.course.domain.mapper.packages.toEntity
import com.uagr.kmp.course.domain.model.packages.PackagesModel
import org.koin.core.annotation.Factory

@Factory
class PackagesLocalDataSourceImpl(
    private val packagesDao: PackagesDao
) : PackagesLocalDataSource {

    override suspend fun clearAndInsertPackages(packages: PackagesModel) {
        packagesDao.clearAndInsertPackages(packages = packages.toEntity())
    }

    override suspend fun getPackages(): PackagesModel? =
        packagesDao.getPackages()?.toDomain()
}
