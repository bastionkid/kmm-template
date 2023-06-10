package com.azuredragon.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform