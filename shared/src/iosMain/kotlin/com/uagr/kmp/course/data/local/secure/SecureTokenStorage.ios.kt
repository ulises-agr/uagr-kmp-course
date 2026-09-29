/*
 * SecureTokenStorage.ios.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.data.local.secure

import com.uagr.kmp.course.utils.constant.Constants
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.convert
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import platform.CoreFoundation.CFDictionaryCreateMutable
import platform.CoreFoundation.CFDictionaryRef
import platform.CoreFoundation.CFDictionarySetValue
import platform.CoreFoundation.CFRelease
import platform.CoreFoundation.CFStringCreateWithCString
import platform.CoreFoundation.CFStringRef
import platform.CoreFoundation.CFTypeRefVar
import platform.CoreFoundation.kCFAllocatorDefault
import platform.CoreFoundation.kCFBooleanTrue
import platform.CoreFoundation.kCFStringEncodingUTF8
import platform.CoreFoundation.kCFTypeDictionaryKeyCallBacks
import platform.CoreFoundation.kCFTypeDictionaryValueCallBacks
import platform.Foundation.CFBridgingRetain
import platform.Foundation.NSData
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.create
import platform.Foundation.dataUsingEncoding
import platform.Security.SecItemAdd
import platform.Security.SecItemCopyMatching
import platform.Security.SecItemDelete
import platform.Security.errSecSuccess
import platform.Security.kSecAttrAccount
import platform.Security.kSecAttrAccessible
import platform.Security.kSecAttrAccessibleWhenUnlockedThisDeviceOnly
import platform.Security.kSecAttrService
import platform.Security.kSecClass
import platform.Security.kSecClassGenericPassword
import platform.Security.kSecMatchLimit
import platform.Security.kSecMatchLimitOne
import platform.Security.kSecReturnData
import platform.Security.kSecValueData

actual fun createSecureTokenStorage(): SecureTokenStorage = IosSecureTokenStorage()

@OptIn(ExperimentalForeignApi::class)
private class IosSecureTokenStorage : SecureTokenStorage {

    private val service = Constants.SECURE_PREFS_NAME
    private val account = Constants.USER_TOKEN

    override suspend fun saveToken(token: String) {
        clearToken()
        val valueData = (token as NSString).dataUsingEncoding(NSUTF8StringEncoding) ?: return
        val query = newKeychainDictionary(capacity = 5)
        val serviceRef = service.toCFString()
        val accountRef = account.toCFString()
        val dataRef = CFBridgingRetain(valueData)
        try {
            CFDictionarySetValue(query, kSecClass, kSecClassGenericPassword)
            CFDictionarySetValue(query, kSecAttrService, serviceRef)
            CFDictionarySetValue(query, kSecAttrAccount, accountRef)
            CFDictionarySetValue(query, kSecValueData, dataRef)
            CFDictionarySetValue(query, kSecAttrAccessible, kSecAttrAccessibleWhenUnlockedThisDeviceOnly)
            SecItemAdd(query, null)
        } finally {
            CFRelease(query)
            CFRelease(serviceRef)
            CFRelease(accountRef)
            dataRef?.let { pointer -> CFRelease(pointer) }
        }
    }

    override suspend fun getToken(): String? = memScoped {
        val query = newKeychainDictionary(capacity = 5)
        val serviceRef = service.toCFString()
        val accountRef = account.toCFString()
        val result = alloc<CFTypeRefVar>()
        try {
            CFDictionarySetValue(query, kSecClass, kSecClassGenericPassword)
            CFDictionarySetValue(query, kSecAttrService, serviceRef)
            CFDictionarySetValue(query, kSecAttrAccount, accountRef)
            CFDictionarySetValue(query, kSecReturnData, kCFBooleanTrue)
            CFDictionarySetValue(query, kSecMatchLimit, kSecMatchLimitOne)

            val status = SecItemCopyMatching(query, result.ptr)
            if (status != errSecSuccess) return@memScoped null

            val data = result.value as? NSData ?: return@memScoped null
            NSString.create(data = data, encoding = NSUTF8StringEncoding)?.toString()
        } finally {
            CFRelease(query)
            CFRelease(serviceRef)
            CFRelease(accountRef)
        }
    }

    override suspend fun clearToken() {
        val query = newKeychainDictionary(capacity = 3)
        val serviceRef = service.toCFString()
        val accountRef = account.toCFString()
        try {
            CFDictionarySetValue(query, kSecClass, kSecClassGenericPassword)
            CFDictionarySetValue(query, kSecAttrService, serviceRef)
            CFDictionarySetValue(query, kSecAttrAccount, accountRef)
            SecItemDelete(query)
        } finally {
            CFRelease(query)
            CFRelease(serviceRef)
            CFRelease(accountRef)
        }
    }

    private fun newKeychainDictionary(capacity: Int): CFDictionaryRef =
        CFDictionaryCreateMutable(
            allocator = kCFAllocatorDefault,
            capacity = capacity.convert(),
            keyCallBacks = kCFTypeDictionaryKeyCallBacks.ptr,
            valueCallBacks = kCFTypeDictionaryValueCallBacks.ptr,
        )!!

    private fun String.toCFString(): CFStringRef =
        CFStringCreateWithCString(
            alloc = kCFAllocatorDefault,
            cStr = this,
            encoding = kCFStringEncodingUTF8,
        )!!
}
