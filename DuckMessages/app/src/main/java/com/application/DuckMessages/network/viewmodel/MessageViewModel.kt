package com.application.DuckMessages.network.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.DuckMessages.network.model.AddMessageRequest
import com.application.DuckMessages.network.model.MessageObject
import com.application.DuckMessages.network.repository.MessageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Sealed class for the message list UI state
sealed class MessageState {
    object Idle : MessageState()
    object Loading : MessageState()
    data class Success(val messages: List<MessageObject>) : MessageState()
    data class Error(val message: String) : MessageState()
}

class MessageViewModel(private val repository: MessageRepository) : ViewModel() {

    private val _messageState = MutableStateFlow<MessageState>(MessageState.Idle)
    val messageState: StateFlow<MessageState> = _messageState

    fun fetchMessages(senderId: String, receiverId: String) {
        viewModelScope.launch {
            _messageState.value = MessageState.Loading
            try {
                val response = repository.getMessages(senderId, receiverId)
                if (response.isSuccessful && response.body() != null) {
                    _messageState.value = MessageState.Success(response.body()!!.data)
                } else {
                    val errorMsg = response.errorBody()?.string() ?: "Failed to fetch messages"
                    _messageState.value = MessageState.Error(errorMsg)
                }
            } catch (e: Exception) {
                _messageState.value = MessageState.Error("An error occurred: ${e.message}")
            }
        }
    }

    fun sendMessage(senderId: String, receiverId: String, content: String) {
        viewModelScope.launch {
            try {
                val request = AddMessageRequest(senderId, receiverId, content)
                val response = repository.sendMessage(request)
                if (response.isSuccessful) {
                    // After a successful send, refresh the message list to show the new message
                    fetchMessages(senderId, receiverId)
                } else {
                    // Optionally, you can handle the send error here, e.g., show a toast
                }
            } catch (e: Exception) {
                // Handle network or other exceptions during send
            }
        }
    }
}