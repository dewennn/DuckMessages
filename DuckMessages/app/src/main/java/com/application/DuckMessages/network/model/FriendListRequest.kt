package com.application.DuckMessages.network.model

import com.google.gson.annotations.SerializedName

data class FriendListRequest(
    @SerializedName("FriendId")
    val friendId: String,

    @SerializedName("DisplayName")
    val displayName: String,

    @SerializedName("MessagePreview")
    val messagePreview: String?,

    @SerializedName("Timestamp")
    val timestamp: String?
)
