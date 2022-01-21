package com.twinkle.twinklechat.chatlist.model

data class ChatModel(
    val content: String?=null,
    val fromID: String?=null,
    val isRead: Boolean?=null,
    val timestamp: Long?=null,
    val toID: String?=null,
    val type: String?=null
)