package org.infomericainc.infod2a.data.remote.source

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.infomericainc.infod2a.util.CommonConstants

internal abstract class D2AKtorProvider {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                    prettyPrint = true
                }
            )
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.d(message, null)
                }
            }
            level = LogLevel.ALL
        }.also { Napier.base(DebugAntilog()) }
    }

    fun HttpRequestBuilder.salesForcePath(
        token: String
    ) {
        url {
            takeFrom(CommonConstants.DATA_2_AI_BASE_URL)
            path("api", "chat", "completions")
            contentType(ContentType.Application.Json)
        }

        headers {
            append(HttpHeaders.Authorization, token)
        }
    }

    fun HttpRequestBuilder.powerAppsPath(
        token: String
    ) {
        url {
            takeFrom(CommonConstants.DATA_2_AI_BASE_URL)
            path("api", "chat", "completions")
            contentType(ContentType.Application.Json)
        }
        headers {
            append(HttpHeaders.Authorization, token)
        }
    }
}