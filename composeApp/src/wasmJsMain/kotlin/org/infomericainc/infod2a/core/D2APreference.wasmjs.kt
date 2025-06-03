package org.infomericainc.infod2a.core

import kotlin.reflect.KClass


internal class WasmJsPreferences : D2APreferences {
    override fun <T> save(key: String, value: T) {
        TODO("Not yet implemented")
    }

    override fun <T : Any> get(key: String, clazz: KClass<T>): T? {
        TODO("Not yet implemented")
    }
}

actual fun getPreferences(): D2APreferences = WasmJsPreferences()