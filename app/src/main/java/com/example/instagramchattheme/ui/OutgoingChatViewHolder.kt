package com.example.instagramchattheme.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramchattheme.R
import com.example.instagramchattheme.model.Chat


class OutgoingChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setChat(chat: Chat) {
        val textView = itemView.findViewById<TextView>(R.id.tvOutgoingMessage)
        textView.text = chat.message
    }
}