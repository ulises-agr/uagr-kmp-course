/*
 * PackagesNetworkDataSource.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.local.datasource.packages

import com.uagr.kmp.course.data.local.database.dao.PackageDAO
import com.uagr.kmp.course.domain.mapper.packages.toDomain
import com.uagr.kmp.course.domain.mapper.packages.toEntity
import com.uagr.kmp.course.domain.model.packages.PackageDataModel
import com.uagr.kmp.course.domain.model.packages.PackageModel
import org.koin.core.annotation.Factory

@Factory
class PackagesLocalDataSourceImp(
    private val packageDAO: PackageDAO
) : PackagesLocalDataSource {
    
    override suspend fun clearAndInsertPackages(packages: List<PackageDataModel>) {
        packageDAO.clearAndInsertPackages(
            packages = packages.map {
                    data  -> data.toEntity()
            })
    }
    
    override suspend fun getPackages(): List<PackageDataModel> =
        packageDAO.getPackages().map {
                data -> data.toDomain()
        }
}