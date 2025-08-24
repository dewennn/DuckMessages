package com.application.DuckMessages.network.model

import com.google.gson.annotations.SerializedName

data class MessageListRequest(
    @SerializedName("SenderId")
    val senderId: String,

    @SerializedName("ReceiverId")
    val receiverId: String
)
