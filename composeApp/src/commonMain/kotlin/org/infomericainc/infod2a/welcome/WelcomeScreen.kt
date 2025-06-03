package org.infomericainc.infod2a.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import infod2a.composeapp.generated.resources.Res
import infod2a.composeapp.generated.resources.welcome_banner
import org.infomericainc.infod2a.chat.getPlatform
import org.infomericainc.infod2a.chat.isAndroidOrIos
import org.infomericainc.infod2a.theme.PoppinsFont
import org.jetbrains.compose.resources.painterResource

@Composable
fun WelcomeScreen() {

    val isAndroidOrIos by remember {
        mutableStateOf(getPlatform().isAndroidOrIos())
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (isAndroidOrIos) {
            D2AMobileWelcomeScreen()
        } else {
            D2ADesktopWelcomeScreen()
        }
    }
}

@Composable
private fun D2AMobileWelcomeScreen() {

}

@Composable
private fun D2ADesktopWelcomeScreen() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Image(
            painter = painterResource(Res.drawable.welcome_banner),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(.6f)
                .fillMaxHeight()
                .blur(5.dp),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Welcome to InfoD2A",
                fontFamily = PoppinsFont(),
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp
            )

            Text(
                "Your nxtGen AI agent designed for your\n business needs",
                fontFamily = PoppinsFont(),
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(
                        top = 10.dp
                    ),
                textAlign = TextAlign.Center
            )

            Button(
                onClick = {

                },
                modifier = Modifier
                    .padding(
                        top = 20.dp
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Get Started",
                    fontFamily = PoppinsFont(),
                    fontWeight = FontWeight.Medium
                )
            }
        }

    }
}