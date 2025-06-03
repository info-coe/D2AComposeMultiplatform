package org.infomericainc.infod2a

import androidx.compose.ui.window.ComposeUIViewController
import org.infomericainc.infod2a.chat.ChatScreen
import org.infomericainc.infod2a.navigation.InfoD2AEntryPoint

fun MainViewController() = ComposeUIViewController { InfoD2AEntryPoint() }