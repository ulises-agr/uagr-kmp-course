/*
 * Constants.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.utils.constant

object Constants {
    // Network
    const val REQUEST_TIMEOUT_MILLIS = 60000L
    const val CONNECT_TIMEOUT_MILLIS = 60000L
    const val SOCKET_TIMEOUT_MILLIS  = 60000L

    // DataBase
    const val DATABASE_NAME = "kmp_course_DB"

    // DataStore
    const val USER_TOKEN = "user_token"
    const val DATASTORE_NAME = "kmp_dataStore"
    const val SECURE_PREFS_NAME = "fintrack_secure_prefs"

    // Device id (UUID persisted locally)
    const val DEVICE_ID_KEY = "device_id"
    const val DEVICE_PREFS_NAME = "fintrack_device_prefs"
}
