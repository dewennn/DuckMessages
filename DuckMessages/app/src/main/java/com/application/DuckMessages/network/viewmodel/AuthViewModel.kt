package com.application.DuckMessages.network.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.DuckMessages.network.model.AuthRequest
import com.application.DuckMessages.network.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    // UI state exposed to the Composables
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    // Handles the login logic
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val response = repository.login(AuthRequest(email, password))
                if (response.isSuccessful && response.body() != null) {
                    _authState.value = AuthState.Success(response.body()!!.token ?: "")
                } else {
                    val errorMsg = response.errorBody()?.string() ?: "Login Failed"
                    _authState.value = AuthState.Error(errorMsg)
                }
            } catch (e: Exception) {
                _authState.value = AuthState.Error("An unexpected error occurred: ${e.message}")
            }
        }
    }

    // Handles the registration logic
    fun register(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val response = repository.register(AuthRequest(email, password))
                if (response.isSuccessful && response.body() != null) {
                    _authState.value = AuthState.Success(response.body()!!.message)
                } else {
                    val errorMsg = response.errorBody()?.string() ?: "Registration Failed"
                    _authState.value = AuthState.Error(errorMsg)
                }
            } catch (e: Exception) {
                _authState.value = AuthState.Error("An unexpected error occurred: ${e.message}")
            }
        }
    }

    fun resetState() {
        _authState.value = AuthState.Idle
    }
}

// Sealed class to represent the different states of the authentication process
sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val data: String) : AuthState()
    data class Error(val message: String) : AuthState()
}