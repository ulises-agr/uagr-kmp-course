/*
 * DateUtils.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.utils.date

import kotlin.time.Clock
import kotlin.time.Instant
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

fun formatRelativeDate(isoDateString: String): String {
    if (isoDateString.isBlank()) return ""
    return try {
        val timeZone = TimeZone.currentSystemDefault()
        val dateInstant = Instant.parse(isoDateString)
        val targetDate = dateInstant.toLocalDateTime(timeZone).date
        val today = Clock.System.now().toLocalDateTime(timeZone).date

        when (targetDate) {
            today -> "Hoy"
            today.minus(1, DateTimeUnit.DAY) -> "Ayer"
            today.plus(1, DateTimeUnit.DAY) -> "Mañana"
            else -> {
                "${targetDate.day} ${targetDate.month}"
            }
        }
    } catch (_: Exception) {
        isoDateString
    }
}
