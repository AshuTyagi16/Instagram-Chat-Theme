package com.example.instagramchattheme.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramchattheme.R
import com.example.instagramchattheme.model.Chat
import com.example.instagramchattheme.model.ChatType

class ChatAdapter : ListAdapter<Chat, RecyclerView.ViewHolder>(chatItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ChatType.INCOMING.ordinal -> {
                IncomingChatViewHolder(
                    layoutInflater.inflate(
                        R.layout.cell_incoming,
                        parent,
                        false
                    )
                )
            }
            ChatType.OUTGOING.ordinal -> {
                OutgoingChatViewHolder(
                    layoutInflater.inflate(
                        R.layout.cell_outgoing,
                        parent,
                        false
                    )
                )
            }
            else -> throw Exception("Unsupported Type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            when (it.chatType) {
                ChatType.INCOMING -> {
                    if (holder is IncomingChatViewHolder)
                        holder.setChat(it)
                }
                ChatType.OUTGOING -> {
                    if (holder is OutgoingChatViewHolder)
                        holder.setChat(it)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        getItem(position)?.let {
            return it.chatType.ordinal
        } ?: run {
            return super.getItemViewType(position)
        }
    }
}

private val chatItemDiffCallback = object : DiffUtil.ItemCallback<Chat>() {
    override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
        return oldItem == newItem
    }

}