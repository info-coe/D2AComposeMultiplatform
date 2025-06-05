package org.infomericainc.infod2a.presentation.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.infomericainc.infod2a.di.getCommonModules
import org.infomericainc.infod2a.presentation.chat.ChatScreen
import org.infomericainc.infod2a.presentation.chat.viewmodel.ChatCompletionViewModel
import org.infomericainc.infod2a.presentation.welcome.WelcomeScreen
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
fun InfoD2AEntryPoint(
    desktopWindowWidth : Int = 0,
    onNavHostReady: suspend (NavController) -> Unit = {}
) {
    val navController = rememberNavController()

    LaunchedEffect(navController) {
        onNavHostReady(navController)
    }

    KoinApplication(
        application = {
            modules(getCommonModules())
        }
    ) {
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
                        desktopWindowWidth = desktopWindowWidth,
                        navController = navController
                    )
                }

                composable(Routes.CHAT.name) {
                    val chatViewModel = koinInject<ChatCompletionViewModel>()
                    val conversations = chatViewModel.conversationUiState.collectAsStateWithLifecycle()
                    val chatUiState = chatViewModel.chatUiState.collectAsStateWithLifecycle()

                    ChatScreen(
                        navController = navController,
                        conversations = conversations.value,
                        chatUiState = chatUiState.value,
                        desktopWindowWidth = desktopWindowWidth,
                        onEvent = chatViewModel::onEvent
                    )
                }
            }
        }
    }
}