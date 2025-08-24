package com.application.DuckMessages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.application.DuckMessages.network.RetrofitInstance
import com.application.DuckMessages.network.repository.AuthRepository
import com.application.DuckMessages.network.repository.FriendRepository
import com.application.DuckMessages.network.repository.MessageRepository
import com.application.DuckMessages.network.viewmodel.AuthViewModel
import com.application.DuckMessages.network.viewmodel.AuthViewModelFactory
import com.application.DuckMessages.network.viewmodel.FriendViewModel
import com.application.DuckMessages.network.viewmodel.FriendViewModelFactory
import com.application.DuckMessages.network.viewmodel.MessageViewModel
import com.application.DuckMessages.network.viewmodel.MessageViewModelFactory
import com.application.DuckMessages.pages.authentication.LoginScreen
import com.application.DuckMessages.pages.authentication.RegisterScreen
import com.application.DuckMessages.pages.messaging.personalMessage.PersonalMessageScreen
import com.application.DuckMessages.pages.chat.ChatScreen
import com.application.DuckMessages.ui.theme.DuckMessagesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DuckMessagesTheme {
                val navController = rememberNavController()

                // View Models
                val authViewModel: AuthViewModel = viewModel(
                    factory = AuthViewModelFactory(AuthRepository(RetrofitInstance.api))
                )
                val friendViewModel: FriendViewModel = viewModel(
                    factory = FriendViewModelFactory(FriendRepository(RetrofitInstance.api))
                )
                val messageViewModel: MessageViewModel = viewModel(
                    factory = MessageViewModelFactory(MessageRepository(RetrofitInstance.api))
                )

                // -- ROUTES --
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        LoginScreen(
                            authViewModel = authViewModel,
                            onNavigateToRegister = {
                                navController.navigate("register")
                            },
                            onLoginSuccess = { userId, displayName ->
                                navController.navigate("main/$userId/$displayName") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable("register") {
                        RegisterScreen(
                            authViewModel = authViewModel,
                            onNavigateToLogin = {
                                navController.navigate("login") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable(
                        route = "main/{userId}/{displayName}",
                        enterTransition = { EnterTransition.None },
                        exitTransition = { ExitTransition.None },
                        popEnterTransition = { EnterTransition.None },
                        popExitTransition = { ExitTransition.None }
                    ){ backStackEntry ->
                        val userId = backStackEntry.arguments?.getString("userId") ?: ""
                        val displayName = backStackEntry.arguments?.getString("displayName") ?: ""
                        ChatScreen(navController, userId = userId, displayName = displayName, friendViewModel = friendViewModel)
                    }

                    composable(
                        route = "personal_message/{senderId}/{receiverId}/{displayName}",
                        enterTransition = {
                            slideInHorizontally (
                                initialOffsetX = { it },
                                animationSpec = tween (350)
                            )
                        },
                        exitTransition = {
                            slideOutHorizontally (
                                targetOffsetX = { -it },
                                animationSpec = tween(350)
                            )
                        },
                        popEnterTransition = {
                            slideInHorizontally(
                                initialOffsetX = { -it },
                                animationSpec = tween(350)
                            )
                        },
                        popExitTransition = {
                            slideOutHorizontally(
                                targetOffsetX = { it },
                                animationSpec = tween(350)
                            )
                        }
                    ){ backStackEntry ->
                        val senderId = backStackEntry.arguments?.getString("senderId") ?: ""
                        val receiverId = backStackEntry.arguments?.getString("receiverId") ?: ""
                        val displayName = backStackEntry.arguments?.getString("displayName") ?: ""
                        PersonalMessageScreen(navController, senderId = senderId, receiverId = receiverId, displayName = displayName, messageViewModel = messageViewModel)
                    }
                }
            }
        }
    }
}