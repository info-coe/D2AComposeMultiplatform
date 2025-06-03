package org.infomericainc.infod2a.domain.model

data class Completion(
    val choice: List<Choice> = listOf(),
)

data class Choice(
    val finishReason: String = "",
    val index: Int = -1,
    val message: Message = Message()
)

data class Message(
    val content: String = "",
    val role: String = ""
)
