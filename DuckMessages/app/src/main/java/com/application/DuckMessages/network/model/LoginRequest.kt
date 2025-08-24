package com.application.DuckMessages.network.model

data class LoginRequest(
    val phoneNumber: String,
    val password: String
)
