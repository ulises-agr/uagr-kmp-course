/*
 * PackagesNetworkDataSource.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.local.datasource

import com.uagr.kmp.course.data.local.database.dao.PackageDAO
import com.uagr.kmp.course.domain.mapper.packages.toDomain
import com.uagr.kmp.course.domain.mapper.packages.toEntity
import com.uagr.kmp.course.domain.model.packages.PackageModel

class PackagesLocalDataSourceImp(
    private val packageDAO: PackageDAO
) : PackagesLocalDataSource {
    
    override suspend fun clearAndInsertPackages(packageModel: PackageModel) {
        packageDAO.clearAndInsertPackages(packagesEntity = packageModel.toEntity())
    }
    
    override suspend fun getPackages(): PackageModel? =
        packageDAO.getPackage()?.toDomain()
    
}