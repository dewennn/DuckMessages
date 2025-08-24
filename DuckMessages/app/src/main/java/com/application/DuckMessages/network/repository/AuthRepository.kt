package com.application.DuckMessages.network.repository

import com.application.DuckMessages.network.ApiService
import com.application.DuckMessages.network.model.AuthResponse
import com.application.DuckMessages.network.model.LoginRequest
import com.application.DuckMessages.network.model.RegisterRequest
import retrofit2.Response


class AuthRepository(private val apiService: ApiService) {
    suspend fun login(request: LoginRequest): Response<AuthResponse> {
        return apiService.loginUser(request)
    }

    suspend fun register(request: RegisterRequest): Response<AuthResponse> {
        return apiService.registerUser(request)
    }
}
