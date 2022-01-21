package com.twinkle.chatversionone.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.twinkle.chatversionone.R
import com.twinkle.chatversionone.databinding.ChatAdapterBinding
import com.twinkle.chatversionone.projectutils.SharedPref
import com.twinkle.twinklechat.chatlist.model.ChatModel
import java.text.SimpleDateFormat

class ChatAdapter(val context: Context, options: FirebaseRecyclerOptions<ChatModel>) :
    FirebaseRecyclerAdapter<ChatModel, ChatAdapter.MyViewHolder>(options) {

    class MyViewHolder(val dataBinding: ChatAdapterBinding) :
        RecyclerView.ViewHolder(dataBinding.root)

    @SuppressLint("SimpleDateFormat")
    val simpleDateFormat = SimpleDateFormat("hh:mm a")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val dataBinding: ChatAdapterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.chat_adapter,
            parent,
            false
        )
        return MyViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: ChatModel) {

        holder.dataBinding.itemMessage.dataBinding.chatdata = model
        holder.dataBinding.itemMessage.dataBinding.data = SharedPref().getStoringValue("uid").toString()
        holder.dataBinding.itemMessage.dataBinding.sdf = simpleDateFormat

    }

}