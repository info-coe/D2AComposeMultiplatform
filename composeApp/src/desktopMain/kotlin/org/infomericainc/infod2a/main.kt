package org.infomericainc.infod2a

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import infod2a.composeapp.generated.resources.Res
import org.infomericainc.infod2a.chat.ChatScreen
import org.infomericainc.infod2a.navigation.InfoD2AEntryPoint
import org.infomericainc.infod2a.welcome.WelcomeScreen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import java.awt.Dimension
import javax.swing.JFrame

fun main() = application {
    val state = rememberWindowState()
    Window(
        onCloseRequest = ::exitApplication,
        title = "InfoD2A",
        state = state,
    ) {
        val awtWindow = this.window as? JFrame
        awtWindow?.minimumSize = Dimension(1000, 600)
        InfoD2AEntryPoint()
    }
}
