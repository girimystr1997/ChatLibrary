package com.twinkle.twinklechat.chatlist.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatLocationModel(
    val lastMessage: String? = "",
    val lastMessageUid: String? = "",
    val messageCount: String? = "0",
    val lastMessageTime: Long? = 0,
    val location: String? = ""
) : Parcelable
