package com.twinkle.chatversionone

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.twinkle.chatversionone.adapter.ChatAdapter
import com.twinkle.chatversionone.databinding.ActivityNewChattingPageBinding
import com.twinkle.chatversionone.projectutils.SharedPref
import com.twinkle.chatversionone.viewmodel.ChattingPageViewModel
import com.twinkle.twinklechat.chatlist.model.ChatLocationModel
import com.twinkle.twinklechat.chatlist.model.ChatModel
import com.twinkle.twinklechat.chatlist.model.ProfileDataModel

class NewChattingPage : AppCompatActivity() {
    lateinit var dataBinding: ActivityNewChattingPageBinding
    lateinit var viewModel: ChattingPageViewModel
    lateinit var popupMenu: PopupMenu
    val database = Firebase.database
    var myRefConv = database.reference.push()
    lateinit var chatAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_new_chatting_page)
        viewModel = ViewModelProvider(this)[ChattingPageViewModel::class.java]

        val profileDataModel = intent?.getParcelableExtra<ProfileDataModel>("profileData")

        viewModel.mychatuid.value = SharedPref().getStoringValue("uid")
        viewModel.otherendchatuid.value = profileDataModel?.chatUid
        viewModel.loadChatBackup()

        popupMenu = PopupMenu(this, dataBinding.cui.dataBinding.menuicon)
        loadPopup()
        dataBinding.cui.dataBinding.profilename.text = profileDataModel?.name
        Glide.with(this).load(profileDataModel?.profilePic)
            .into(dataBinding.cui.dataBinding.profilepic)

        dataBinding.cui.dataBinding.backicon.setOnClickListener {
            onBackPressed()
        }

        dataBinding.cui.dataBinding.menuicon.setOnClickListener {
            popupMenu.show()
        }

        viewModel.chatPath.observe(this, {
            if (!it.isNullOrEmpty()) {
                val options: FirebaseRecyclerOptions<ChatModel> =
                    FirebaseRecyclerOptions.Builder<ChatModel>()
                        .setQuery(
                            database.getReference("usersConversations").child(it).orderByValue(),
                            ChatModel::class.java
                        )
                        .build()
                recycloader(options)
            }
        })


        dataBinding.cui.dataBinding.imageView5.setOnClickListener {
            val timings = System.currentTimeMillis()
            val chatModel = ChatModel(
                dataBinding.cui.dataBinding.editTextTextPersonName.text.toString(),
                SharedPref().getStoringValue("uid"),
                false,
                timings,
                profileDataModel?.chatUid,
                "text"
            )
            dataBinding.cui.dataBinding.editTextTextPersonName.text.clear()
            if (viewModel.chatPath.value.isNullOrEmpty()) {
                val chatLocationModel = ChatLocationModel(
                    chatModel.content.toString(),
                    SharedPref().getStoringValue("uid"),
                    "00",
                    chatModel.timestamp!!,
                    myRefConv.key.toString()
                )
                database.getReference("users")
                    .child(SharedPref().getStoringValue("uid")!!)
                    .child("conversations")
                    .child(profileDataModel?.chatUid!!)
                    .setValue(chatLocationModel)
                database.getReference("users")
                    .child(profileDataModel.chatUid!!)
                    .child("conversations")
                    .child(SharedPref().getStoringValue("uid")!!)
                    .setValue(chatLocationModel)
                database.getReference("usersConversations").child(myRefConv.key!!).push()
                    .setValue(chatModel)
            } else {
                val chatLocationModel = ChatLocationModel(
                    chatModel.content.toString(),
                    SharedPref().getStoringValue("uid"),
                    "00",
                    chatModel.timestamp!!,
                    viewModel.chatPath.value
                )
                database.getReference("users")
                    .child(SharedPref().getStoringValue("uid")!!)
                    .child("conversations")
                    .child(profileDataModel?.chatUid!!)
                    .setValue(chatLocationModel)
                database.getReference("users")
                    .child(profileDataModel.chatUid!!)
                    .child("conversations")
                    .child(SharedPref().getStoringValue("uid")!!)
                    .setValue(chatLocationModel)

                database.getReference("usersConversations").child(viewModel.chatPath.value!!).push()
                    .setValue(chatModel)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun recycloader(options: FirebaseRecyclerOptions<ChatModel>) {
        chatAdapter = ChatAdapter(this, options)
        dataBinding.cui.dataBinding.chatlists.adapter = chatAdapter
        chatAdapter.startListening()
        chatAdapter.notifyDataSetChanged()
    }

    private fun loadPopup() {
        popupMenu.menuInflater.inflate(R.menu.chatmenu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.title) {
                "Block" -> {
                    item.title = "UnBlock"
                    dataBinding.cui.dataBinding.chatbox.visibility = View.GONE
                    dataBinding.cui.dataBinding.blockbox.visibility = View.VISIBLE
                    popupMenu.dismiss()
                }
                "UnBlock" -> {
                    item.title = "Block"
                    dataBinding.cui.dataBinding.chatbox.visibility = View.VISIBLE
                    dataBinding.cui.dataBinding.blockbox.visibility = View.GONE
                    popupMenu.dismiss()
                }
                else -> {
                    popupMenu.dismiss()
                }
            }
            true
        }
    }
}