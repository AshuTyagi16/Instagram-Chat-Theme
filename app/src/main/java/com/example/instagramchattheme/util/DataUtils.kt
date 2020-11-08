package com.example.instagramchattheme.util

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.instagramchattheme.R
import com.example.instagramchattheme.model.Chat
import com.example.instagramchattheme.model.ChatTheme
import com.example.instagramchattheme.model.ChatType

class DataUtils(private val context: Context) {

    private val messages = listOf(
        "Hello, thank you for using tully service. Can I help you with something?",
        "Welcome back. Happy to see you again. What can we do for you today?",
        "Hi, we would like to let you know that the chat is monitored or recorded for quality assurance",
        "Hello, I will need your contact details to update the further process. Kindly provide your phone number, email etc.",
        "We need to access your screen and co-browse together to address your issue. May we proceed with that?",
        "I would like to recommend solution that fits your requirements.",
        "Please hold for a moment while I am transferring you to agent",
        "We need some additional information prior to proceeding. Please share the required information?",
        "May I request you to provide your current billing address?",
        "May I request you to verify your current residence address?",
        "Thank you for visiting our website. Hope to see you once again. Good day.",
        "Glad I could help \uD83D\uDE42 Wish you a good day!",
        "We understand too many options can be confusing. May I assist in helping you to decide?",
        "Hello! We are pleased to inform you that we are offering a discount on “shoes”. Would to like to take advantage of the limited period deal?"
    )

    fun getChatList(): List<Chat> {
        val chatList = ArrayList<Chat>(50)
        repeat(50) {
            val chatType = if (it % 2 == 0) ChatType.INCOMING else ChatType.OUTGOING
            chatList.add(Chat(it, messages.random(), chatType))
        }
        return chatList
    }

    fun getChatThemes(): List<ChatTheme> {
        val themes = ArrayList<ChatTheme>(10)
        themes.add(
            ChatTheme(
                "Theme 1",
                ContextCompat.getColor(context, R.color.theme_one_start),
                ContextCompat.getColor(context, R.color.theme_one_end)
            )
        )
        themes.add(
            ChatTheme(
                "Theme 2",
                ContextCompat.getColor(context, R.color.theme_two_start),
                ContextCompat.getColor(context, R.color.theme_two_end)
            )
        )
        themes.add(
            ChatTheme(
                "Theme 3",
                ContextCompat.getColor(context, R.color.theme_three_start),
                ContextCompat.getColor(context, R.color.theme_three_end)
            )
        )
        themes.add(
            ChatTheme(
                "Theme 4",
                ContextCompat.getColor(context, R.color.theme_four_start),
                ContextCompat.getColor(context, R.color.theme_four_end)
            )
        )
        themes.add(
            ChatTheme(
                "Theme 5",
                ContextCompat.getColor(context, R.color.theme_five_start),
                ContextCompat.getColor(context, R.color.theme_five_end)
            )
        )
        themes.add(
            ChatTheme(
                "Theme 6",
                ContextCompat.getColor(context, R.color.theme_six_start),
                ContextCompat.getColor(context, R.color.theme_seven_end)
            )
        )
        themes.add(
            ChatTheme(
                "Theme 7",
                ContextCompat.getColor(context, R.color.theme_seven_start),
                ContextCompat.getColor(context, R.color.theme_seven_end)
            )
        )
        return themes
    }
}