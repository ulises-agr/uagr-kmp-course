package com.uagr.kmp.course.domain.usecase.user

import com.uagr.kmp.course.data.network.model.request.login.LoginRequest
import com.uagr.kmp.course.domain.model.dashboard.DashboardModel
import com.uagr.kmp.course.domain.model.login.TokensModel
import com.uagr.kmp.course.domain.repository.bone.LoginRepository
import com.uagr.kmp.course.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

class LoginUseCase (
    private val loginRepository: LoginRepository,
){
    suspend operator fun invoke(
        url: String,
        email: String,
        password: String,
        deviceId: String
    ) : Flow<NetworkResult<TokensModel>> =
        loginRepository.login(
            url = url,
            loginRequest = LoginRequest(
                email = email,
                password = password,
                device_id = deviceId,
            )
        )
}