package com.uagr.kmp.course.data.local.datasource.bone

import com.uagr.kmp.course.data.network.model.request.login.LoginRequest
import com.uagr.kmp.course.domain.model.login.TokensModel
import com.uagr.kmp.course.utils.network.NetworkResult

interface LoginNetworkDataSource {
    suspend fun login(
        url: String,
        loginRequest: LoginRequest
    ): NetworkResult<TokensModel>
}