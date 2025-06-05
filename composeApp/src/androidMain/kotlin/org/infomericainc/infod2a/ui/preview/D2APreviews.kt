package org.infomericainc.infod2a.ui.preview

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import org.infomericainc.infod2a.domain.model.Message
import org.infomericainc.infod2a.presentation.chat.ChatScreen
import org.infomericainc.infod2a.presentation.chat.viewmodel.ChatUiState

@Preview
@Composable
private fun ChatScreenPreview() {
    Surface {
        ChatScreen(
            navController = rememberNavController(),
            conversations = listOf(
                Message(
                    content = "hey",
                    role = "user"
                )
            ),
            chatUiState = ChatUiState(),
            desktopWindowWidth = 0
        ) { }
    }
}