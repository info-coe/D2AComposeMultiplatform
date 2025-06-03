package org.infomericainc.infod2a.core

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import org.koin.compose.KoinContext
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication
import kotlin.reflect.KClass


internal class AndroidPreferences(
    context: Context
) : D2APreferences {
    var masterKeyAlias = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    val encryptedSharedPreferences = EncryptedSharedPreferences.create(
        context,
        "secure_prefs",
        masterKeyAlias,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun <T> save(key: String, value: T) {
        encryptedSharedPreferences.apply {
            edit().apply {
                when (value) {
                    is String -> putString(key, value)
                    is Boolean -> putBoolean(key, value)
                }
                commit()
            }
        }
    }

    override fun <T : Any> get(key: String, clazz: KClass<T>): T? {
        return when (clazz) {
            String::class -> TODO()
            Boolean::class -> encryptedSharedPreferences.getBoolean(key, false) as T
            else -> TODO()
        }
    }
}

actual fun getPreferences(): D2APreferences = AndroidPreferences(context = TODO())

internal const val dataStoreFileName = "dice.preferences_pb"
