/*
 * ApiErrorResponse.kt
 * Copyright (c) 2026. All rights reserved
 */

package com.uagr.kmp.course.data.network.model.response.base

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable
data class ErrorDetailObject(
    val code: String? = null,
    val message: String? = null,
    val details: JsonElement? = null,
    val request_id: String? = null,
)

@Serializable
data class ApiErrorResponse(
    val error: ErrorDetailObject? = null,
    val detail: JsonElement? = null,
    val message: String? = null,
) {
    fun parseErrorMessage(): String? {
        if (error != null) {
            val innerDetails = error.details
            if (innerDetails != null) {
                val parsed = parseDetailsElement(innerDetails)
                if (!parsed.isNullOrBlank()) return parsed
            }
            if (!error.message.isNullOrBlank()) return error.message
        }

        if (detail != null) {
            val parsed = parseDetailsElement(detail)
            if (!parsed.isNullOrBlank()) return parsed
        }

        return message
    }

    private fun parseDetailsElement(element: JsonElement): String? {
        return when (element) {
            is JsonArray -> {
                element.mapNotNull { item ->
                    if (item is JsonObject) {
                        item["message"]?.jsonPrimitive?.content
                            ?: item["msg"]?.jsonPrimitive?.content
                    } else {
                        try {
                            item.jsonPrimitive.content
                        } catch (e: Exception) {
                            null
                        }
                    }
                }.filter { it.isNotBlank() }.joinToString("\n").takeIf { it.isNotBlank() }
            }
            is JsonObject -> {
                element["message"]?.jsonPrimitive?.content
                    ?: element["msg"]?.jsonPrimitive?.content
            }
            else -> {
                try {
                    element.jsonPrimitive.content.takeIf { it.isNotBlank() }
                } catch (e: Exception) {
                    null
                }
            }
        }
    }
}
