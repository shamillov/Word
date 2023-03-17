package com.shamilov.core

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform