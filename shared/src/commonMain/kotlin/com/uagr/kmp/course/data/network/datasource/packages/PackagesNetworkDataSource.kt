package com.uagr.kmp.course.data.network.datasource.packages

import com.uagr.kmp.course.domain.model.packages.PackageModel
import com.uagr.kmp.course.utils.network.NetworkResult

interface PackagesNetworkDataSource {
    suspend fun getPackages(url : String) : NetworkResult<PackageModel>
}