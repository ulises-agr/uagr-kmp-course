package com.uagr.kmp.course

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform