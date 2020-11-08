package com.example.instagramchattheme.model

data class Chat(
    val id: Int,
    val message: String,
    val chatType: ChatType
)

enum class ChatType {
    INCOMING, OUTGOING
}