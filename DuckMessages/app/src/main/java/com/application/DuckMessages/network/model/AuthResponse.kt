package com.application.DuckMessages.network.model

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("userId")
    val userId: String,

    @SerializedName("displayName")
    val displayName: String,

    @SerializedName("phoneNumber")
    val phoneNumber: String
)

data class AuthResponse(
    @SerializedName("status")
    val status: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: UserData
)
