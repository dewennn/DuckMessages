package com.application.DuckMessages.network.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.DuckMessages.network.model.AddFriendRequest
import com.application.DuckMessages.network.model.FriendItem
import com.application.DuckMessages.network.repository.FriendRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Sealed class to represent the different UI states
sealed class FriendListState {
    object Idle : FriendListState()
    object Loading : FriendListState()
    data class Success(val friends: List<FriendItem>) : FriendListState()
    data class Error(val message: String) : FriendListState()
}

sealed class AddFriendState {
    object Idle : AddFriendState()
    object Loading : AddFriendState()
    data class Success(val message: String) : AddFriendState()
    data class Error(val message: String) : AddFriendState()
}

class FriendViewModel(private val repository: FriendRepository) : ViewModel() {

    private val _friendListState = MutableStateFlow<FriendListState>(FriendListState.Idle)
    val friendListState: StateFlow<FriendListState> = _friendListState

    fun fetchFriends(userId: String) {
        viewModelScope.launch {
            _friendListState.value = FriendListState.Loading
            try {
                val response = repository.getFriendList(userId)

                if (response.isSuccessful && response.body() != null) {
                    _friendListState.value = FriendListState.Success(response.body()!!.data)
                }
                else {
                    val errorMsg = response.errorBody()?.string() ?: "Failed to fetch friends"
                    _friendListState.value = FriendListState.Error(errorMsg)
                }
            }
            catch (e: Exception) {
                _friendListState.value = FriendListState.Error("An error occurred: ${e.message}")
            }
        }
    }

    private val _addFriendState = MutableStateFlow<AddFriendState>(AddFriendState.Idle)
    val addFriendState: StateFlow<AddFriendState> = _addFriendState

    fun addFriend(senderId: String, receiverPhoneNumber: String) {
        viewModelScope.launch {
            _addFriendState.value = AddFriendState.Loading
            try {
                val request = AddFriendRequest(senderId, receiverPhoneNumber)
                val response = repository.addFriend(request) // Assumes this exists in your repository

                if (response.isSuccessful && response.body() != null) {
                    _addFriendState.value = AddFriendState.Success(response.body()!!.message)
                    // After successfully adding, refresh the friend list
                    fetchFriends(senderId)
                } else {
                    val errorMsg = response.errorBody()?.string() ?: "Failed to add friend"
                    _addFriendState.value = AddFriendState.Error(errorMsg)
                }
            } catch (e: Exception) {
                _addFriendState.value = AddFriendState.Error("An error occurred: ${e.message}")
            }
        }
    }

    // --- Helper function to reset the add friend state ---
    fun resetAddFriendState() {
        _addFriendState.value = AddFriendState.Idle
    }
}