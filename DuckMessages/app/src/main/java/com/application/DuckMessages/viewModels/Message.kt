package com.application.DuckMessages.viewModels

data class Message(
    val text: String,
    val timestamp: String,
    val isIncoming: Boolean,
    val isRead: Boolean = false
)