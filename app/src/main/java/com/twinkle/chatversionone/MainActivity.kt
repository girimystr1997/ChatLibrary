package com.twinkle.chatversionone

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.twinkle.chatversionone.adapter.ChatListAdapter
import com.twinkle.chatversionone.adapter.NewChatAdapter
import com.twinkle.chatversionone.databinding.ActivityMainBinding
import com.twinkle.chatversionone.projectutils.SharedPref
import com.twinkle.twinklechat.chatlist.model.ChatLocationModel
import com.twinkle.twinklechat.chatlist.model.ProfileDataModel

class MainActivity : AppCompatActivity() {
    lateinit var dataBinding: ActivityMainBinding
    lateinit var adapter: ChatListAdapter
    lateinit var adapterNew: NewChatAdapter
    val database = Firebase.database
    val chatlistarray = ArrayList<ChatLocationModel>()
    val profileListArray = ArrayList<ProfileDataModel>()
    val profileDataId = ArrayList<String>()
    lateinit var profileDataModel: ProfileDataModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        profileDataModel = intent.getParcelableExtra("profileData")!!

        SharedPref().setStoringValue("uid",profileDataModel.chatUid)

        database.getReference("users").child(profileDataModel.chatUid!!).child("profile")
            .setValue(profileDataModel)

        database.getReference("users")
            .child(profileDataModel.chatUid!!)
            .child("conversations")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        chatlistarray.clear()
                        profileDataId.clear()
                        for (data in snapshot.children) {
                            data.getValue(ChatLocationModel::class.java).let {
                                if (it != null) {
                                    chatlistarray.add(it)
                                    profileDataId.add(data.key!!)
                                }
                            }
                        }
                        loadRecyclerview(chatlistarray, profileDataId)
                    }else{
                        loadRecyclerview(chatlistarray, profileDataId)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("exception", error.message)
                }
            })


    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadRecyclerview(
        chalistarray: ArrayList<ChatLocationModel>,
        profileData: ArrayList<String>
    ) {
        if (chatlistarray.isNotEmpty()) {
            adapter = ChatListAdapter(this, chalistarray, profileData)
            dataBinding.recyclerview.adapter = adapter
            adapter.notifyDataSetChanged()
        }else{
            database.getReference("users").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        profileListArray.clear()
                        for (data in snapshot.children){
                            if (!data.key.equals(profileDataModel.chatUid)){
                                val dataModel = snapshot.child(data.key.toString()).child("profile").getValue(ProfileDataModel::class.java)
                                profileListArray.add(dataModel!!)
                            }
                        }
                        recycloader(profileListArray)
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }

            })
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun recycloader(profileListArray: ArrayList<ProfileDataModel>) {
        adapterNew = NewChatAdapter(this, profileListArray)
        dataBinding.recyclerview.adapter = adapterNew
        adapterNew.notifyDataSetChanged()
    }
}