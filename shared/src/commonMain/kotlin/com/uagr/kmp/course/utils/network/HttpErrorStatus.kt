package com.uagr.kmp.course.utils.network

enum class HttpErrorStatus {
    UNAUTHORIZED,         // 401, 403 (Credenciales inválidas)
    REGISTERED_MAIL,      // 409 (Correo ya registrado)
    VALIDATION_ERROR,     // 422 (Error de validación)
    SERVER_ERROR,         // 500+ (Error interno del servidor)
    UNKNOWN;              // Otro error

    companion object {
        fun fromCode(code: Int?): HttpErrorStatus {
            return when (code) {
                401, 403 -> UNAUTHORIZED
                409 -> REGISTERED_MAIL
                422 -> VALIDATION_ERROR
                in 500..599 -> SERVER_ERROR
                else -> UNKNOWN
            }
        }
    }
}