package com.uagr.kmp.course.data.network.datasource

import com.uagr.kmp.course.domain.model.PackageModel
import com.uagr.kmp.course.utils.network.NetworkResult

interface PackagesNetworkDataSource {
    suspend fun getPackages(url : String) : NetworkResult<PackageModel>
}