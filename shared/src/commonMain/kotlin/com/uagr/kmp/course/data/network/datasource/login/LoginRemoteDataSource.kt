/*
 * LoginRemoteDataSource.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.network.datasource.login

import com.uagr.kmp.course.domain.model.login.LoginCredentialsModel
import com.uagr.kmp.course.domain.model.login.LoginDataModel
import com.uagr.kmp.course.utils.network.api.NetworkResult

interface LoginRemoteDataSource {
    suspend fun login(
        url: String,
        loginCredentialsModel: LoginCredentialsModel,
    ): NetworkResult<LoginDataModel>
}
