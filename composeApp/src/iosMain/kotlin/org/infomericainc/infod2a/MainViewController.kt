package org.infomericainc.infod2a

import androidx.compose.ui.window.ComposeUIViewController
import org.infomericainc.infod2a.chat.ChatScreen

fun MainViewController() = ComposeUIViewController { ChatScreen() }