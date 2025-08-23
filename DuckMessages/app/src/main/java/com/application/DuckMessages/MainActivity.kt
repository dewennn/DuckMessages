package com.application.DuckMessages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.application.DuckMessages.pages.authentication.AuthScreen
import com.application.DuckMessages.pages.call.CallScreen
import com.application.DuckMessages.pages.messaging.personalMessage.PersonalMessageScreen
import com.application.DuckMessages.pages.chat.ChatScreen
import com.application.DuckMessages.pages.update.UpdateScreen
import com.application.DuckMessages.ui.theme.DuckMessagesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DuckMessagesTheme {
                val navController = rememberNavController()

                // -- ROUTES --
                NavHost(navController = navController, startDestination = "auth") {
                    // AUTH
                    composable(
                        route = "auth",
                        enterTransition = { EnterTransition.None },
                        exitTransition = { ExitTransition.None },
                        popEnterTransition = { EnterTransition.None },
                        popExitTransition = { ExitTransition.None }
                    ){
                        AuthScreen ()
                    }

                    // HOME
                    composable(
                        route = "chats",
                        enterTransition = { EnterTransition.None },
                        exitTransition = { ExitTransition.None },
                        popEnterTransition = { EnterTransition.None },
                        popExitTransition = { ExitTransition.None }
                    ){
                        ChatScreen(navController)
                    }

                    composable(
                        route = "updates",
                        enterTransition = { EnterTransition.None },
                        exitTransition = { ExitTransition.None },
                        popEnterTransition = { EnterTransition.None },
                        popExitTransition = { ExitTransition.None }
                    ){
                        UpdateScreen(navController)
                    }

                    composable(
                        route = "communities",
                        enterTransition = { EnterTransition.None },
                        exitTransition = { ExitTransition.None },
                        popEnterTransition = { EnterTransition.None },
                        popExitTransition = { ExitTransition.None }
                    ){
                        ChatScreen(navController)
                    }

                    composable(
                        route = "calls",
                        enterTransition = { EnterTransition.None },
                        exitTransition = { ExitTransition.None },
                        popEnterTransition = { EnterTransition.None },
                        popExitTransition = { ExitTransition.None }
                    ){
                        CallScreen(navController)
                    }

                    // INNER LAYERS
                    composable(
                        route = "personal_message",
                        enterTransition = {
                            slideInHorizontally (
                                initialOffsetX = { it }, // The 'it' is the full width, so it slides in from the far right
                                animationSpec = tween (350)
                            )
                        },
                        exitTransition = {
                            slideOutHorizontally (
                                targetOffsetX = { -it }, // Slides out to the far left
                                animationSpec = tween(350)
                            )
                        },
                        popEnterTransition = {
                            slideInHorizontally(
                                initialOffsetX = { -it }, // When coming back, it slides in from the left
                                animationSpec = tween(350)
                            )
                        },
                        popExitTransition = {
                            slideOutHorizontally(
                                targetOffsetX = { it }, // When going back, it slides out to the right
                                animationSpec = tween(350)
                            )
                        }
                    ){
                        PersonalMessageScreen(navController)
                    }
                }
            }
        }
    }
}