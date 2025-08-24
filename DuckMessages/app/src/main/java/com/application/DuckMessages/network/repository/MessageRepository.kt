package com.application.DuckMessages.network.repository

import com.application.DuckMessages.network.ApiService
import com.application.DuckMessages.network.model.*
import retrofit2.Response

class MessageRepository(private val apiService: ApiService) {
    suspend fun getMessages(senderId: String, receiverId: String): Response<MessageListResponse> {
        return apiService.getMessageList(senderId, receiverId)
    }

    suspend fun sendMessage(request: AddMessageRequest): Response<AddMessageResponse> {
        return apiService.sendMessage(request)
    }
}