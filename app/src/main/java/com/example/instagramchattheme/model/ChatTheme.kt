package com.example.instagramchattheme.model

data class ChatTheme(
    val name: String,
    val startColor: Int,
    val endColor: Int
) {
    override fun toString(): String {
        return name
    }
}