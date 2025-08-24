package com.application.DuckMessages.network.model

import com.google.gson.annotations.SerializedName

data class FriendItem(
    @SerializedName("friendId")
    val friendId: String,

    @SerializedName("displayName")
    val displayName: String,

    @SerializedName("messagePreview")
    val messagePreview: String?,

    @SerializedName("timestamp")
    val timestamp: String?
)

data class FriendListResponse(
    @SerializedName("status")
    val status: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: List<FriendItem>
)