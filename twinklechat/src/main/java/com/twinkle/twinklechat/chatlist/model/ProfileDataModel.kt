package com.twinkle.twinklechat.chatlist.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileDataModel(
    val name: String? = "",
    val profilePic:String? = "",
    val chatUid:String? = "",
    val mobileNumber:String? = "",
    val timestamp: String? = ""
):Parcelable