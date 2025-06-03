package org.infomericainc.infod2a.domain.usecase.chatcompletion

import kotlinx.coroutines.flow.flow
import org.infomericainc.infod2a.data.remote.mapper.toCompletion
import org.infomericainc.infod2a.data.remote.mapper.toMessageRequestList
import org.infomericainc.infod2a.data.remote.request.ChatCompletionRequest
import org.infomericainc.infod2a.data.remote.request.ParamsRequest
import org.infomericainc.infod2a.domain.model.Message
import org.infomericainc.infod2a.domain.repository.ChatCompletionRepository
import org.infomericainc.infod2a.util.Resource
import org.infomericainc.infod2a.util.extension.getModel
import org.infomericainc.infod2a.util.extension.getToolId
import org.infomericainc.infod2a.util.extension.toPathType
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class GetResponseUseCase : KoinComponent {

    private val chatCompletionRepository: ChatCompletionRepository by inject()

    suspend operator fun invoke(
        messages: List<Message>,
        accountType: String?
    ) = flow {

        emit(Resource.Loading())

        val pathType = accountType?.toPathType() ?: return@flow

        val completionDto = chatCompletionRepository.getChatCompletion(
            key = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjFjNmQ3MzliLWE3NGYtNDQ0Yi1hNjQ0LWFhYmNkY2Y5MjlmNSJ9.xDOt046JcLYZirwvuW7p4TuRxP-euIsDbLNcbPpn5HY",
            pathType = pathType,
            request = ChatCompletionRequest(
                model = pathType.getModel(),
                tool_ids = listOf(
                    pathType.getToolId()
                ),
                paramsRequest = ParamsRequest(
                    functionCalling = "non-native"
                ),
                messageRequests = messages.toMessageRequestList()
            ),
        )

        val completion = completionDto?.toCompletion()

        val response = completion?.choice?.get(0)?.message

        emit(Resource.Success(response))

    }
}