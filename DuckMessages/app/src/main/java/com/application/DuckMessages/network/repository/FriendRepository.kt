package com.application.DuckMessages.network.repository

import com.application.DuckMessages.network.ApiService
import com.application.DuckMessages.network.model.AddFriendRequest
import com.application.DuckMessages.network.model.AddFriendResponse
import com.application.DuckMessages.network.model.FriendListResponse
import retrofit2.Response

class FriendRepository(private val apiService: ApiService) {
    suspend fun getFriendList(userId: String): Response<FriendListResponse> {
        return apiService.getFriendList(userId)
    }

    suspend fun addFriend(request: AddFriendRequest): Response<AddFriendResponse> {
        return apiService.addFriend(request)
    }
}