package org.infomericainc.infod2a

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL
import androidx.compose.ui.tooling.preview.Preview
import org.infomericainc.infod2a.presentation.navigation.InfoD2AEntryPoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            InfoD2AEntryPoint()
        }
    }
}

@Preview(
    device = PIXEL
)
@Composable
fun AppAndroidPreview() {
    InfoD2AEntryPoint()
}