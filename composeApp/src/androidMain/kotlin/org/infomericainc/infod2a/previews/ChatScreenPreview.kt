package org.infomericainc.infod2a.previews

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import org.infomericainc.infod2a.chat.ChatScreen

@Preview(
    device = Devices.DESKTOP,
    widthDp = 1080,
    heightDp = 768
)
@Composable
private fun ChatScreenPreview() {
    Surface {
        ChatScreen()
    }
}