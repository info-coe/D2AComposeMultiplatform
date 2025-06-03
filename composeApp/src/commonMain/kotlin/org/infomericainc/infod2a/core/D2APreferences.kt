package org.infomericainc.infod2a.core

import kotlin.reflect.KClass

interface D2APreferences {
    fun <T> save(key: String, value: T)
    fun <T : Any> get(key: String, clazz: KClass<T>): T?
}

inline fun <reified T : Any> D2APreferences.put(key: String, value: T) = save(key, value)
inline fun <reified T : Any> D2APreferences.get(key: String): T? = get(key, T::class)

expect fun getPreferences(): D2APreferences


