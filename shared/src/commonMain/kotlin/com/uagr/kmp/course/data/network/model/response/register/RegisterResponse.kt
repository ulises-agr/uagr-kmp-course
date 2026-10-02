/*
 * RegisterResponse.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.network.model.response.register

import com.uagr.kmp.course.data.network.model.response.base.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val user: RegisterUserResponse?,
    val tokens: UserTokenResponse?,
) : BaseResponse()
