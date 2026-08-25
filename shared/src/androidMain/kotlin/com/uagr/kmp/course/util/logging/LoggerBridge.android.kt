/*
 * LoggerBridge.android.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.util.logging

import com.uagr.kmp.course.util.logs.KmpLogger
import com.uagr.kmp.course.util.logs.TypeLog

actual fun logCustom(
    message: String?,
    tag: String,
    typeLog: TypeLog,
) {
    KmpLogger.log(
        message = message.orEmpty(),
        tag = tag,
        typeLog = typeLog,
    )
}
