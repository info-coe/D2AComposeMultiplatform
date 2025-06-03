package org.infomericainc.infod2a.domain.repository

import org.infomericainc.infod2a.data.remote.dto.ChatCompletionDto
import org.infomericainc.infod2a.data.remote.request.ChatCompletionRequest
import org.infomericainc.infod2a.data.remote.util.PathType

internal interface ChatCompletionRepository {
    suspend fun getChatCompletion(
        key: String,
        pathType: PathType,
        request: ChatCompletionRequest
    ): ChatCompletionDto?
}