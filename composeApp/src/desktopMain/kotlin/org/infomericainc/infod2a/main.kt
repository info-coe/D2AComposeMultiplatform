package org.infomericainc.infod2a

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.infomericainc.infod2a.presentation.navigation.InfoD2AEntryPoint
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
