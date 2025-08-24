package com.application.DuckMessages.network.model

data class RegisterRequest(
    val displayName: String,
    val phoneNumber: String,
    val password: String
)
