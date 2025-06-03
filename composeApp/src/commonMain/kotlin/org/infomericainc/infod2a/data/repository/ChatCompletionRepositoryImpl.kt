package org.infomericainc.infod2a.data.repository

import org.infomericainc.infod2a.domain.repository.ChatCompletionRepository
import org.infomericainc.infod2a.data.remote.dto.ChatCompletionDto
import org.infomericainc.infod2a.data.remote.request.ChatCompletionRequest
import org.infomericainc.infod2a.data.remote.service.RemoteChatCompletionService
import org.infomericainc.infod2a.data.remote.util.PathType

internal class ChatCompletionRepositoryImpl(
    private val remoteChatCompletionService: RemoteChatCompletionService
) : ChatCompletionRepository {

    override suspend fun getChatCompletion(
        key: String,
        pathType: PathType,
        request: ChatCompletionRequest
    ): ChatCompletionDto? {
        return remoteChatCompletionService.getChatCompletion(key, pathType, request)
    }

}