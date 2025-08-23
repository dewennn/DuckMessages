package com.application.DuckMessages.pages.authentication

import com.application.DuckMessages.network.RetrofitInstance
import com.application.DuckMessages.network.repository.AuthRepository
import com.application.DuckMessages.network.viewmodel.AuthViewModel
import com.application.DuckMessages.network.viewmodel.AuthViewModelFactory
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun AuthScreen() {
    val navController = rememberNavController()
    // A simple way to provide the ViewModelFactory
    val authViewModel: AuthViewModel = viewModel(
        factory = AuthViewModelFactory(AuthRepository(RetrofitInstance.api))
    )

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                authViewModel = authViewModel,
                onNavigateToRegister = {
                    navController.navigate("register")
                }
            )
        }
        composable("register") {
            RegisterScreen(
                authViewModel = authViewModel,
                onNavigateToLogin = {
                    navController.navigate("login") {
                        // Pop up to login to clear the back stack
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
    }
}