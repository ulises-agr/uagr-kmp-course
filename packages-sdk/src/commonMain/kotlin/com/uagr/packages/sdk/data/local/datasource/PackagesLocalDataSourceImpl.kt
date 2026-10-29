/*
 * PackagesLocalDataSourceImpl.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.packages.sdk.data.local.datasource

import com.uagr.packages.sdk.data.local.dao.PackagesDao
import com.uagr.packages.sdk.domain.mapper.toDomain
import com.uagr.packages.sdk.domain.mapper.toEntity
import com.uagr.packages.sdk.domain.model.PackagesDataModel

class PackagesLocalDataSourceImpl(
    private val packagesDao: PackagesDao,
) : PackagesLocalDataSource {

    override suspend fun clearAndInsertPackages(packages: List<PackagesDataModel>) {
        packagesDao.clearAndInsertPackages(packages = packages.map { data -> data.toEntity() })
    }

    override suspend fun getPackages(): List<PackagesDataModel> =
        packagesDao.getPackages().map { data -> data.toDomain() }
}
