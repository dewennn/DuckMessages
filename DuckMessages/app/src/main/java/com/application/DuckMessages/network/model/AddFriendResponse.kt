package com.application.DuckMessages.network.model

import com.google.gson.annotations.SerializedName

data class AddFriendResponse(
    @SerializedName("status")
    val status: Int,

    @SerializedName("message")
    val message: String,
)
