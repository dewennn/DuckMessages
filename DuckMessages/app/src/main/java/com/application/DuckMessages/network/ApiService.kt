package com.application.DuckMessages.network


import com.application.DuckMessages.network.model.AddFriendRequest
import com.application.DuckMessages.network.model.AddFriendResponse
import com.application.DuckMessages.network.model.AddMessageRequest
import com.application.DuckMessages.network.model.AddMessageResponse
import com.application.DuckMessages.network.model.AuthResponse
import com.application.DuckMessages.network.model.FriendListResponse
import com.application.DuckMessages.network.model.LoginRequest
import com.application.DuckMessages.network.model.MessageListResponse
import com.application.DuckMessages.network.model.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("api/v1/user/login")
    suspend fun loginUser(@Body request: LoginRequest): Response<AuthResponse>

    @POST("api/v1/user/register")
    suspend fun registerUser(@Body request: RegisterRequest): Response<AuthResponse>

    @GET("api/v1/user/friend")
    suspend fun getFriendList(@Query("userId") userId: String): Response<FriendListResponse>

    @POST("api/v1/user/friend")
    suspend fun addFriend(@Body request: AddFriendRequest): Response<AddFriendResponse>

    @GET("api/v1/text-message")
    suspend fun getMessageList(
        @Query("senderId") senderId: String,
        @Query("receiverId") receiverId: String
    )
    : Response<MessageListResponse>

    @POST("api/v1/text-message")
    suspend fun sendMessage(@Body request: AddMessageRequest): Response<AddMessageResponse>
}