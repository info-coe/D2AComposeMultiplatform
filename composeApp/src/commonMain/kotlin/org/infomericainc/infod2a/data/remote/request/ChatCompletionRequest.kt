package org.infomericainc.infod2a.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ChatCompletionRequest(
    val model: String? = null,
    val tool_ids: List<String>? = null,
    @SerialName("params")
    val paramsRequest: ParamsRequest? = null,
    @SerialName("messages")
    val messageRequests: List<MessageRequest>? = null
)

@Serializable
internal data class ParamsRequest(
    @SerialName("function_calling")
    val functionCalling: String? = null
)

@Serializable
data class MessageRequest(
    @SerialName("content")
    val content: String? = null,
    @SerialName("role")
    val role: String? = null
)