package com.application.DuckMessages.network


import com.application.DuckMessages.network.model.AuthRequest
import com.application.DuckMessages.network.model.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/login")
    suspend fun loginUser(@Body authRequest: AuthRequest): Response<AuthResponse>

    @POST("api/register")
    suspend fun registerUser(@Body authRequest: AuthRequest): Response<AuthResponse>
}