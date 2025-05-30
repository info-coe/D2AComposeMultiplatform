package org.infomericainc.infod2a.chat

enum class PlatformType {
    Android,
    Ios,
    Desktop,
    Web;
}

expect fun getPlatform() : PlatformType