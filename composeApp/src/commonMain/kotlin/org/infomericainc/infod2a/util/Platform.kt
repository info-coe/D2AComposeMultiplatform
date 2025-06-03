package org.infomericainc.infod2a.util

enum class PlatformType {
    Android,
    Ios,
    Desktop,
    Web;
}

expect fun getPlatform(): PlatformType

fun PlatformType.isAndroidOrIos(): Boolean =
    (this.name == PlatformType.Android.name) || (this.name == PlatformType.Ios.name)