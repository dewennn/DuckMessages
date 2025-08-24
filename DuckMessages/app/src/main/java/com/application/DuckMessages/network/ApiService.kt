package com.application.DuckMessages.network


import com.application.DuckMessages.network.model.AuthResponse
import com.application.DuckMessages.network.model.LoginRequest
import com.application.DuckMessages.network.model.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/v1/user/login")
    suspend fun loginUser(@Body request: LoginRequest): Response<AuthResponse>

    @POST("api/v1/user/register")
    suspend fun registerUser(@Body request: RegisterRequest): Response<AuthResponse>
}