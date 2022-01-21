package com.twinkle.twinklechat.chatlist.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatlistModel(
    val imageurl: String?,
    val name: String?,
    val message: String?,
    val time: String?,
    val m_count: String?,
    val id:String?,
    val chatUid:String?
): Parcelable