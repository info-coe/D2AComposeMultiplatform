package org.infomericainc.infod2a

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import androidx.navigation.ExperimentalBrowserHistoryApi
import androidx.navigation.bindToNavigation
import kotlinx.browser.document
import kotlinx.browser.window
import org.infomericainc.infod2a.chat.ChatScreen
import org.infomericainc.infod2a.navigation.InfoD2AEntryPoint

@OptIn(ExperimentalComposeUiApi::class, ExperimentalBrowserHistoryApi::class)
fun main() {
    val body = document.body ?: return
    ComposeViewport(body) {
        InfoD2AEntryPoint {
            window.bindToNavigation(it)
        }
    }
}