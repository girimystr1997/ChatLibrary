package com.twinkle.chatversionone.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.widget.Filter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.twinkle.chatversionone.NewChattingPage
import com.twinkle.chatversionone.R
import com.twinkle.chatversionone.databinding.ChatListAdapterBinding
import com.twinkle.twinklechat.chatlist.model.ChatLocationModel
import com.twinkle.twinklechat.chatlist.model.ProfileDataModel
import java.text.SimpleDateFormat

class NewChatAdapter(
    val context: Context,
    var chatListModel: ArrayList<ProfileDataModel>
) :
    BaseRecyclerAdapter<ChatListAdapterBinding>(context) {

    lateinit var profileDataModelData: ProfileDataModel

    override fun setViewHolder(holder: ChatListAdapterBinding, position: Int) {
        holder.chatviews.dataBinding.profiledata = chatListModel[position]
        profileDataModelData = chatListModel[position]

        holder.chatviews.setOnClickListener {
            with(context) {
                startActivity(
                    Intent(this, NewChattingPage::class.java)
                        .putExtra("profileData", profileDataModelData)
                )
            }
        }
    }

    override fun getLayout() = R.layout.chat_list_adapter

    override fun getCount() = chatListModel.size

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }
}