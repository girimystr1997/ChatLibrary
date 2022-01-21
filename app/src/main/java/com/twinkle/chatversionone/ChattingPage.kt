package com.twinkle.chatversionone

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.twinkle.chatversionone.databinding.ActivityChattingPageBinding
import com.twinkle.twinklechat.chatlist.model.ChatModel
import com.twinkle.twinklechat.chatlist.model.ChatlistModel
import com.twinkle.twinklechat.viewmodel.ChatPageViewModel

class ChattingPage : AppCompatActivity() {
    lateinit var dataBinding: ActivityChattingPageBinding
    lateinit var viewModel: ChatPageViewModel

    val database = Firebase.database
    var myRefConv = database.reference.push()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_chatting_page)
        viewModel = ViewModelProvider(this)[ChatPageViewModel::class.java]
        dataBinding.apply {
            this.lifecycleOwner = this@ChattingPage
        }
        val chatlist =
            intent?.getBundleExtra("chatdata")?.getParcelable<ChatlistModel>("chatmodels")
        dataBinding.chatui.dataBinding.chatdata = chatlist
        dataBinding.chatui.dataBinding.chatvm = viewModel

        dataBinding.chatui.dataBinding.profilepic.clipToOutline = true
        val popupMenu = PopupMenu(this, dataBinding.chatui.dataBinding.menuicon)
        popupMenu.menuInflater.inflate(R.menu.chatmenu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.title) {
                "Block" -> {
                    item.title = "UnBlock"
                    dataBinding.chatui.dataBinding.chatbox.visibility = View.GONE
                    dataBinding.chatui.dataBinding.blockbox.visibility = View.VISIBLE
                    popupMenu.dismiss()
                }
                "UnBlock" -> {
                    item.title = "Block"
                    dataBinding.chatui.dataBinding.chatbox.visibility = View.VISIBLE
                    dataBinding.chatui.dataBinding.blockbox.visibility = View.GONE
                    popupMenu.dismiss()
                }
                else -> {
                    popupMenu.dismiss()
                }
            }
            true
        }
        viewModel.backpressed.observe(this, {
            if (it) {
                onBackPressed()
            }
        })
        viewModel.menupressed.observe(this, {
            if (it) {
                println("here")
                popupMenu.show()
            }
        })
        viewModel.sendpressed.observe(this, {
            if (it) {
                val timings = System.currentTimeMillis()
                database.getReference("users").child("mainuid").child("conversations")
                    .child(chatlist?.chatUid!!)
                    .child("location")
                    .setValue(myRefConv.key)
                    .addOnSuccessListener {
                        database.getReference("users")
                            .child(chatlist.chatUid!!)
                            .child("conversations")
                            .child("mainuid")
                            .child("location")
                            .setValue(myRefConv.key)
                            .addOnSuccessListener {
                                val chatModel = ChatModel(
                                    dataBinding.chatui.dataBinding.editTextTextPersonName.text.toString(),
                                    "mainuid",
                                    false,
                                    timings,
                                    chatlist.chatUid,
                                    "text"
                                )
                                database.getReference("usersconversations")
                                    .child(myRefConv.key!!).push().setValue(chatModel)
                            }
                    }
            }
        })
    }
}