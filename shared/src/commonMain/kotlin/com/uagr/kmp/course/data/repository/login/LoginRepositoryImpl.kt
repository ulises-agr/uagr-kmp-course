
package com.uagr.kmp.course.data.repository.login

import com.uagr.kmp.course.data.local.datastore.AppDataStore
import com.uagr.kmp.course.data.network.datasource.login.LoginRemoteDataSource
import com.uagr.kmp.course.data.network.model.request.login.LoginRequest
import com.uagr.kmp.course.data.network.model.response.login.LoginResponse
import com.uagr.kmp.course.domain.mapper.login.toDomain
import com.uagr.kmp.course.domain.model.login.LoginModel
import com.uagr.kmp.course.domain.repository.login.LoginRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.network.safeApiCall

class LoginRepositoryImpl(
    private val remoteDataSource: LoginRemoteDataSource,
    private val appDataStore: AppDataStore
) : LoginRepository {

    override suspend fun login(
        email: String,
        password: String
    ): Boolean {

        val deviceId = appDataStore.getOrCreateDeviceId()

        val request = LoginRequest(
            email = email,
            password = password,
            deviceId = deviceId
        )

        val result = safeApiCall<LoginResponse, LoginModel>(
            apiCall = {
                remoteDataSource.login(request)
            },
            transform = {
                it.toDomain()
            }
        )

        return when (result) {
            is NetworkResult.Success -> {
                val token = result.response.accessToken

                if (token.isNotBlank()) {
                    appDataStore.saveUserToken(token)
                    true
                } else {
                    false
                }
            }

            is NetworkResult.Error -> {
                println("Login Error -> ${result.message}")
                false
            }
        }
    }
}
