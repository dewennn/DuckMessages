package com.application.DuckMessages.network.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.DuckMessages.network.model.LoginRequest
import com.application.DuckMessages.network.model.RegisterRequest
import com.application.DuckMessages.network.model.UserData
import com.application.DuckMessages.network.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    // UI state exposed to the Composables
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    // Handles the login logic
    fun login(phoneNumber: String, password: String) {
        // Launch a coroutine in the viewModelScope
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            // Try the request
            try {
                val response = repository.login(LoginRequest(phoneNumber, password))

                // Check response and update state based on result
                if (response.isSuccessful && response.body() != null) {
                    _authState.value = AuthState.Success(response.body()!!.data)
                } else {
                    val errorMsg = response.errorBody()?.string() ?: "Login Failed"
                    _authState.value = AuthState.Error(errorMsg)
                }
            }
            catch (e: Exception) {
                _authState.value = AuthState.Error("An unexpected error occurred: ${e.message}")
            }
        }
    }

    // Handles the registration logic
    fun register(displayName: String, phoneNumber: String, password: String) {
        // Launch a coroutine in the viewModelScope
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            // Try the request
            try {
                val response = repository.register(RegisterRequest(displayName, phoneNumber, password))

                // Check response and update state based on result
                if (response.isSuccessful && response.body() != null) {
                    _authState.value = AuthState.Success(response.body()!!.data)
                } else {
                    val errorMsg = response.errorBody()?.string() ?: "Registration Failed"
                    _authState.value = AuthState.Error(errorMsg)
                }
            } catch (e: Exception) {
                _authState.value = AuthState.Error("An unexpected error occurred: ${e.message}")
            }
        }
    }

    // Resets the authentication state to Idle
    fun resetState() {
        _authState.value = AuthState.Idle
    }
}

// Sealed class to represent the different states of the authentication process
sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val data: UserData) : AuthState()
    data class Error(val message: String) : AuthState()
}