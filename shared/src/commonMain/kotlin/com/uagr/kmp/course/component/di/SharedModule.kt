/*
 * SharedModule.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.kmp.di

import com.kmp.presentation.welcome.viewmodel.CardViewModel
import com.kmp.presentation.welcome.viewmodel.WelcomeViewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val viewmodelModule = module {
    viewModelOf(constructor = ::WelcomeViewModel)
    viewModelOf(constructor = ::CardViewModel)
}

fun initKoin(config: KoinAppDeclaration? = null){
    startKoin {
        config?.invoke(this)
        modules(
            viewmodelModule,
        )
    }
}
