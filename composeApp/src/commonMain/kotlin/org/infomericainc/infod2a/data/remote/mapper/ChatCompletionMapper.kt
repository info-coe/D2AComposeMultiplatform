package org.infomericainc.infod2a.data.remote.mapper


import org.infomericainc.infod2a.domain.model.Choice
import org.infomericainc.infod2a.domain.model.Completion
import org.infomericainc.infod2a.domain.model.Message
import org.infomericainc.infod2a.data.remote.dto.ChatCompletionDto
import org.infomericainc.infod2a.data.remote.dto.ChoiceDto
import org.infomericainc.infod2a.data.remote.dto.MessageDto
import org.infomericainc.infod2a.data.remote.request.MessageRequest

internal fun ChatCompletionDto.toCompletion(): Completion = Completion(
    choice = choiceDto.toChoicesList()
)

internal fun List<ChoiceDto?>?.toChoicesList(): List<Choice> = this?.map {
    Choice(
        finishReason = it?.finishReason ?: "",
        index = it?.index ?: -1,
        message = it?.messageDto?.toMessage() ?: Message()
    )
} ?: listOf()

internal fun MessageDto.toMessage() = Message(
    content = content ?: "",
    role = role ?: ""
)

internal fun List<Message>.toMessageRequestList() = map {
    MessageRequest(
        content = it.content,
        role = it.role
    )
}