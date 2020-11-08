package com.example.instagramchattheme.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramchattheme.R
import com.example.instagramchattheme.model.Chat

class IncomingChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setChat(chat: Chat) {
        itemView.findViewById<TextView>(R.id.tvIncomingMessage).text = chat.message
    }
}