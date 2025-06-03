package org.infomericainc.infod2a.presentation.chat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.infomericainc.infod2a.domain.model.Message
import org.infomericainc.infod2a.domain.usecase.chatcompletion.GetResponseUseCase
import org.infomericainc.infod2a.util.Resource

internal class ChatCompletionViewModel(
    private val getResponseUseCase: GetResponseUseCase
) : ViewModel() {

    private val mutableChatUiState: MutableStateFlow<ChatUiState> = MutableStateFlow(ChatUiState())
    val chatUiState = mutableChatUiState.asStateFlow()

    private val mutableConversationUiState: MutableStateFlow<List<Message>> = MutableStateFlow(
        listOf()
    )
    val conversationUiState = mutableConversationUiState.asStateFlow()


    fun onEvent(event: ChatCompletionEvent) {
        when (event) {
            is ChatCompletionEvent.GetResponse -> getResponse(event.message, event.accountType)
            is ChatCompletionEvent.StopResponse -> stopResponse()
            is ChatCompletionEvent.ClearConversation -> clearConversation()
        }
    }

    private fun clearConversation() {
        mutableConversationUiState.update {
            listOf()
        }
    }

    private fun getResponse(
        message: String,
        accountType: String
    ) {
        viewModelScope.launch {
            mutableConversationUiState.update {
                mutableConversationUiState.value + Message(
                    content = message,
                    role = "user"
                )
            }


            getResponseUseCase(
                messages = mutableConversationUiState.value,
                accountType = accountType
            )
                .onEach { state ->
                    when (state) {
                        is Resource.Loading -> {
                            mutableChatUiState.update {
                                it.copy(
                                    isResponding = true
                                )
                            }
                        }

                        is Resource.Success -> {
                            if (state.data == null) {
                                mutableChatUiState.update {
                                    it.copy(
                                        isResponding = false,
                                        error = "Unable to get the chat response"
                                    )
                                }
                                return@onEach
                            }

                            mutableConversationUiState.update {
                                mutableConversationUiState.value + state.data
                            }

                            mutableChatUiState.update {
                                it.copy(
                                    isResponding = false,
                                )
                            }
                        }

                        is Resource.Error -> {
                            mutableChatUiState.update {
                                it.copy(
                                    isResponding = false,
                                    error = state.message
                                )
                            }
                        }
                    }

                }
                .launchIn(this)
        }
    }

    private fun stopResponse() {
        viewModelScope.cancel()
        val conversations = mutableConversationUiState.value
        val updatedConversation =
            if (conversations.isNotEmpty()) conversations.dropLast(1) else conversations
        mutableConversationUiState.update {
            updatedConversation
        }
        mutableChatUiState.update {
            it.copy(
                isResponding = false
            )
        }
    }
}