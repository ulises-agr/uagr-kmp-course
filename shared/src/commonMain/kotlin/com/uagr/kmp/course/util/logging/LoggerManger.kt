/*
 * LoggerManger.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.util.logging

import com.uagr.kmp.course.util.constant.Constants
import com.uagr.kmp.course.util.logs.TypeLog

expect fun logCustom(
    message: String?,
    tag: String = Constants.KMP_LOG,
    typeLog: TypeLog,
)
