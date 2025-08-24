package com.application.DuckMessages.network.model

import com.google.gson.annotations.SerializedName

data class AddFriendRequest(
    @SerializedName("senderId")
    val senderId: String,

    @SerializedName("receiverPhoneNumber")
    val receiverPhoneNumber: String
)
