package org.infomericainc.infod2a.core


internal class IOSPreferences : D2APreferences {
    override fun <T> save(key: String, value: T) {
        TODO("Not yet implemented")
    }

    override fun <T> get(key: String): T? {
        TODO("Not yet implemented")
    }
}

actual fun getPreferences(): D2APreferences = IOSPreferences()