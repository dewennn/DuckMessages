package com.application.DuckMessages.network.model

import com.google.gson.annotations.SerializedName

data class MessageObject(
    @SerializedName("messageId")
    val messageId: Long,

    @SerializedName("content")
    val content: String?,

    @SerializedName("sentAt")
    val sentAt: String?,

    @SerializedName("isRead")
    val isRead: Boolean,

    @SerializedName("isSender")
    val isSender: Boolean
)

data class MessageListResponse(
    @SerializedName("status")
    val status: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("data")
    val data: List<MessageObject>
)
