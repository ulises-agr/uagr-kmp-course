package com.uagr.kmp.course.utils.network

import com.uagr.kmp.course.domain.model.base.ErrorDialogModel
import course.shared.generated.resources.Res
import course.shared.generated.resources.accept
import course.shared.generated.resources.email_alredy_registered
import course.shared.generated.resources.error
import course.shared.generated.resources.invalid_credentials
import course.shared.generated.resources.network_error
import course.shared.generated.resources.please_try_again_later
import course.shared.generated.resources.server_error
import org.jetbrains.compose.resources.getString
import org.koin.core.annotation.Factory

@Factory
class NetworkErrorHandler {
    suspend fun getErrorMessage(
        errorType: NetworkErrorType,
        httpCode: Int? = null,
        serverMessage: String? = null,
    ): String {
        val status = HttpErrorStatus.fromCode(httpCode)

        return when (status) {
            HttpErrorStatus.REGISTERED_MAIL ->
                getString(resource = Res.string.email_alredy_registered)

            HttpErrorStatus.UNAUTHORIZED ->
                getString(Res.string.invalid_credentials)

            HttpErrorStatus.VALIDATION_ERROR ->
                serverMessage?.takeIf { it.isNotBlank() && !it.startsWith("Error HTTP") }
                    ?: getString(Res.string.invalid_credentials)

            HttpErrorStatus.SERVER_ERROR ->
                getString(Res.string.server_error)

            HttpErrorStatus.UNKNOWN -> {
                if (errorType == NetworkErrorType.NETWORK || errorType == NetworkErrorType.TIMEOUT) {
                    getString(Res.string.network_error)
                } else {
                    serverMessage ?: getString(Res.string.please_try_again_later)
                }
            }
        }
    }
    suspend fun buildErrorDialogModel(
        errorType: NetworkErrorType,
        httpCode: Int? = null,
        serverMessage: String? = null,
    ): ErrorDialogModel {
        val message = getErrorMessage(errorType, httpCode, serverMessage)
        return ErrorDialogModel(
            title = getString(resource = Res.string.error),
            message = message,
            primaryButtonText = getString(resource = Res.string.accept),
        )
    }
}