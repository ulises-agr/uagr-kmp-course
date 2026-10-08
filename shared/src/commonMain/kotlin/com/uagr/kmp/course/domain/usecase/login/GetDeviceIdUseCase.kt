/*
// GetDeviceIdUseCase.kt
// Created by Karla Judith Cervantes Higareda on 08/10/26.
// Copyright (c) 2026. All rights reserved.
*/
package com.uagr.kmp.course.domain.usecase.login

import com.uagr.kmp.course.data.local.datastore.AppDataStore
import org.koin.core.annotation.Factory

@Factory
class GetDeviceIdUseCase(
    private val appDataStore: AppDataStore
) {
    suspend operator fun invoke(): String = appDataStore.getOrCreateDeviceId()
}
