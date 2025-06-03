package org.infomericainc.infod2a.presentation.chat.viewmodel


sealed class ChatCompletionEvent {
    data class GetResponse(
        val message: String,
        val accountType: String
    ) : ChatCompletionEvent()

    data object ClearConversation : ChatCompletionEvent()
    data object StopResponse : ChatCompletionEvent()
}


data class ChatUiState(
    val isResponding: Boolean = false,
    val error: String = ""
)

enum class AccountType {
    SALESFORCE,
    POWERAPPS
}