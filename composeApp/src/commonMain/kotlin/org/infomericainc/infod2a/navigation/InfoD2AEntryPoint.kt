package org.infomericainc.infod2a.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.infomericainc.infod2a.chat.ChatScreen
import org.infomericainc.infod2a.welcome.WelcomeScreen

@Composable
fun InfoD2AEntryPoint(
    onNavHostReady: suspend (NavController) -> Unit = {}
) {
    val navController = rememberNavController()

    LaunchedEffect(navController) {
        onNavHostReady(navController)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = Routes.WELCOME.name,
            enterTransition = {
                slideInVertically() + fadeIn()
            },
            exitTransition = {
                fadeOut()
            }
        ) {
            composable(Routes.WELCOME.name) {
                WelcomeScreen(
                    navController = navController
                )
            }

            composable(Routes.CHAT.name) {
                ChatScreen(
                    navController = navController
                )
            }
        }
    }
}