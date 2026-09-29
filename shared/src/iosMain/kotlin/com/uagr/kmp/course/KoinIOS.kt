/*
 * KoinIOS.kt
 * Copyright (c) 2026. All rights reserved
*/
package com.uagr.kmp.course

import com.uagr.kmp.course.di.initKoin
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun initKoinIOS() {
    Napier.base(DebugAntilog())
    initKoin()
}
