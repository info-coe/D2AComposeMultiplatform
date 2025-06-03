package org.infomericainc.infod2a.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import infod2a.composeapp.generated.resources.Res
import infod2a.composeapp.generated.resources.logo
import infod2a.composeapp.generated.resources.welcome
import infod2a.composeapp.generated.resources.welcome_banner
import org.infomericainc.infod2a.chat.getPlatform
import org.infomericainc.infod2a.chat.isAndroidOrIos
import org.infomericainc.infod2a.navigation.Routes
import org.infomericainc.infod2a.theme.PoppinsFont
import org.jetbrains.compose.resources.painterResource

@Composable
fun WelcomeScreen(
    navController: NavController
) {

    val isAndroidOrIos by remember {
        mutableStateOf(getPlatform().isAndroidOrIos())
    }

    if (isAndroidOrIos) {
        D2AMobileWelcomeScreen {
            navController.navigate(Routes.CHAT.name)
        }
    } else {
        D2ADesktopWelcomeScreen {
            navController.navigate(Routes.CHAT.name)
        }
    }

}

@Composable
private fun D2AMobileWelcomeScreen(
    onGetStarted: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Image(
            painter = painterResource(Res.drawable.welcome),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.6f),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxHeight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(Res.drawable.logo),
                contentDescription = "",
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
            )
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
                    .alpha(.7f)
                    .padding(
                        top = 5.dp
                    ),
                textAlign = TextAlign.Center
            )

            Button(
                onClick = onGetStarted,
                modifier = Modifier
                    .padding(
                        top = 10.dp
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Get Started",
                    fontFamily = PoppinsFont(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp
                )
            }
        }

    }
}

@Composable
private fun D2ADesktopWelcomeScreen(
    onGetStarted: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Image(
            painter = painterResource(Res.drawable.welcome),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(.6f)
                .fillMaxHeight(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(Res.drawable.logo),
                contentDescription = "",
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
            )
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
                    .alpha(.7f)
                    .padding(
                        top = 5.dp
                    ),
                textAlign = TextAlign.Center
            )

            Button(
                onClick = onGetStarted,
                modifier = Modifier
                    .padding(
                        top = 10.dp
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Get Started",
                    fontFamily = PoppinsFont(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp
                )
            }
        }

    }
}