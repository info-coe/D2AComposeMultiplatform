package org.infomericainc.infod2a

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform