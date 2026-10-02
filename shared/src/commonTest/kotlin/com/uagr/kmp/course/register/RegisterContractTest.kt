package com.uagr.kmp.course.register

import com.uagr.kmp.course.data.network.model.request.RegisterRequest
import com.uagr.kmp.course.data.network.model.response.register.RegisterResponse
import com.uagr.kmp.course.domain.mapper.register.toDomain
import com.uagr.kmp.course.domain.usecase.register.RegisterValidationError
import com.uagr.kmp.course.domain.usecase.register.ValidateRegisterFormUseCase
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertFailsWith
import kotlinx.serialization.SerializationException

class RegisterContractTest {
    private val json = Json { ignoreUnknownKeys = true }
    private val validate = ValidateRegisterFormUseCase()

    @Test
    fun requestContainsOnlyTheFieldsAcceptedByTheEndpoint() {
        val request = RegisterRequest("Karla", "karla@example.com", "FinTrack123!")
        val body = json.parseToJsonElement(json.encodeToString(request)).jsonObject
        assertEquals(setOf("name", "email", "password"), body.keys)
    }

    @Test
    fun responseWithoutSuccessEnvelopeMapsUserAndTokens() {
        val response = json.decodeFromString<RegisterResponse>(
            """{
                "user": {
                    "id": "user-id", "name": "Karla", "email": "karla@example.com",
                    "currency": "MXN", "is_active": true
                },
                "tokens": {
                    "access_token": "test-access", "refresh_token": "test-refresh",
                    "token_type": "bearer", "expires_in": 7200
                }
            }"""
        ).toDomain()
        assertEquals("user-id", response.user.id)
        assertEquals("karla@example.com", response.user.email)
        assertEquals("test-access", response.tokens.accessToken)
        assertEquals("test-refresh", response.tokens.refreshToken)
        assertEquals(7200L, response.tokens.expiresIn)
    }

    @Test
    fun invalidResponseCannotBeTreatedAsSuccessfulRegistration() {
        assertFailsWith<SerializationException> {
            json.decodeFromString<RegisterResponse>("""{"detail":"Email already registered"}""")
        }
    }

    @Test
    fun incompleteInvalidAndMismatchedFormsAreRejected() {
        assertEquals(RegisterValidationError.REQUIRED_FIELDS, validate(" ", "a@b.com", "123", "123"))
        assertEquals(RegisterValidationError.INVALID_EMAIL, validate("Karla", "invalid", "123", "123"))
        assertEquals(RegisterValidationError.PASSWORD_MISMATCH, validate("Karla", "a@b.com", "123", "124"))
        assertNull(validate("Karla", " karla@example.com ", "FinTrack123!", "FinTrack123!"))
    }
}
