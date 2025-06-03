package org.infomericainc.infod2a.data.remote.service

import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import org.infomericainc.infod2a.data.remote.dto.ChatCompletionDto
import org.infomericainc.infod2a.data.remote.request.ChatCompletionRequest
import org.infomericainc.infod2a.data.remote.source.D2AKtorProvider
import org.infomericainc.infod2a.data.remote.util.PathType

internal class RemoteChatCompletionService : D2AKtorProvider() {

    suspend fun getChatCompletion(
        key: String,
        pathType: PathType,
        request: ChatCompletionRequest
    ): ChatCompletionDto? {
        return try {
            client.post {
                when (pathType) {
                    PathType.POWER_APPS -> {
                        powerAppsPath(key)
                    }

                    PathType.SALES_FORCE -> {
                        salesForcePath(key)
                    }
                }
                setBody(request)
            }.body<ChatCompletionDto>()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}