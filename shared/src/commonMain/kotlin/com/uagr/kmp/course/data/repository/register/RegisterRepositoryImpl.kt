package com.uagr.kmp.course.data.repository.register

import com.uagr.kmp.course.data.local.datastore.AppDataStore
import com.uagr.kmp.course.data.network.datasource.register.RegisterRemoteDataSource
import com.uagr.kmp.course.data.network.model.request.register.RegisterRequest
import com.uagr.kmp.course.data.network.model.response.register.RegisterResponse
import com.uagr.kmp.course.domain.mapper.register.toDomain
import com.uagr.kmp.course.domain.model.register.RegisterModel
import com.uagr.kmp.course.domain.repository.register.RegisterRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import com.uagr.kmp.course.utils.network.safeApiCall

class RegisterRepositoryImpl(
    private val remoteDataSource: RegisterRemoteDataSource,
    private val appDataStore: AppDataStore
) : RegisterRepository {

    override suspend fun register(
        name: String,
        email: String,
        password: String
    ): Boolean {

        val request = RegisterRequest(
            name = name,
            email = email,
            password = password
        )

        val result = safeApiCall<RegisterResponse, RegisterModel>(
            apiCall = {
                remoteDataSource.register(request)
            },
            transform = {
                it.toDomain()
            }
        )

        return when (result) {

            is NetworkResult.Success -> {
                val accessToken = result.response.accessToken

                if (accessToken.isBlank()) {
                    println("Register Error -> access token vacio")
                    false
                } else {
                    appDataStore.saveUserToken(accessToken)
                    true
                }
            }

            is NetworkResult.Error -> {
                println("Register Error -> ${result.message}")
                false
            }
        }
    }
}