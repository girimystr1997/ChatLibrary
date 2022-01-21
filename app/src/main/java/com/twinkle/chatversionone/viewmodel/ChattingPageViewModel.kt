package com.twinkle.chatversionone.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.twinkle.twinklechat.chatlist.model.ChatLocationModel
import java.util.*

class ChattingPageViewModel : ViewModel() {

    var database = FirebaseDatabase.getInstance()
    var mychatuid = MutableLiveData<String>()
    var otherendchatuid = MutableLiveData<String>()
    var chatPath = MutableLiveData<String>()

    fun loadChatBackup() {
        database.getReference("users")
            .child(mychatuid.value.toString())
            .child("conversations")
            .child(otherendchatuid.value.toString())
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        snapshot.getValue(ChatLocationModel::class.java).let {
                            if (it != null) {
                                chatPath.value = it.location
                            }
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    error.toException().stackTrace
                }

            })
    }
}