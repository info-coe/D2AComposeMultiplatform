package org.infomericainc.infod2a.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ChatCompletionDto(
    @SerialName("choices")
    val choiceDto: List<ChoiceDto?>? = null,
    @SerialName("created")
    val created: Int? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("model")
    val model: String? = null,
    @SerialName("object")
    val objectX: String? = null,
    @SerialName("service_tier")
    val serviceTier: String? = null,
    @SerialName("system_fingerprint")
    val systemFingerprint: String? = null,
    @SerialName("usage")
    val usage: Usage? = null
)

@Serializable
internal data class ChoiceDto(
    @SerialName("finish_reason")
    val finishReason: String? = null,
    @SerialName("index")
    val index: Int? = null,
    @SerialName("message")
    val messageDto: MessageDto? = null
)

@Serializable
internal data class CompletionTokensDetailsDto(
    @SerialName("accepted_prediction_tokens")
    val acceptedPredictionTokens: Int? = null,
    @SerialName("audio_tokens")
    val audioTokens: Int? = null,
    @SerialName("reasoning_tokens")
    val reasoningTokens: Int? = null,
    @SerialName("rejected_prediction_tokens")
    val rejectedPredictionTokens: Int? = null
)

@Serializable
internal data class MessageDto(
    @SerialName("content")
    val content: String? = null,
    @SerialName("role")
    val role: String? = null
)

@Serializable
internal data class PromptTokensDetails(
    @SerialName("audio_tokens")
    val audioTokens: Int? = null,
    @SerialName("cached_tokens")
    val cachedTokens: Int? = null
)

@Serializable
internal data class Usage(
    @SerialName("completion_tokens")
    val completionTokens: Int? = null,
    @SerialName("completion_tokens_details")
    val completionTokensDetailsDto: CompletionTokensDetailsDto? = null,
    @SerialName("prompt_tokens")
    val promptTokens: Int? = null,
    @SerialName("prompt_tokens_details")
    val promptTokensDetails: PromptTokensDetails? = null,
    @SerialName("total_tokens")
    val totalTokens: Int? = null
)