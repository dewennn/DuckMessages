package com.application.DuckMessages.network.repository

import com.application.DuckMessages.network.ApiService
import com.application.DuckMessages.network.model.AuthRequest


class AuthRepository(private val apiService: ApiService) {
    suspend fun login(authRequest: AuthRequest) = apiService.loginUser(authRequest)
    suspend fun register(authRequest: AuthRequest) = apiService.registerUser(authRequest)
}
