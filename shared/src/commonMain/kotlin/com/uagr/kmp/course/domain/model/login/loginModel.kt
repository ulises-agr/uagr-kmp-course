/*
 * loginModel.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.model.login

import com.uagr.kmp.course.domain.model.user.userTokensModel

data class loginModel(
    val tokens: userTokensModel,
)
