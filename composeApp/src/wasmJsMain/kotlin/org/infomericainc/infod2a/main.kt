package org.infomericainc.infod2a

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import androidx.navigation.ExperimentalBrowserHistoryApi
import androidx.navigation.bindToNavigation
import kotlinx.browser.document
import kotlinx.browser.window
import org.infomericainc.infod2a.di.getCommonModules
import org.infomericainc.infod2a.presentation.navigation.InfoD2AEntryPoint
import org.koin.core.context.GlobalContext.startKoin

@OptIn(ExperimentalComposeUiApi::class, ExperimentalBrowserHistoryApi::class)
fun main() {
    val body = document.body ?: return
    startKoin {
        modules(getCommonModules())
    }
    ComposeViewport(body) {
        InfoD2AEntryPoint {
            window.bindToNavigation(it)
        }
    }
}