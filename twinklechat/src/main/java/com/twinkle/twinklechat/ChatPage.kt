package com.twinkle.twinklechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.twinkle.twinklechat.chatlist.model.ChatlistModel
import com.twinkle.twinklechat.databinding.ActivityChatPageBinding
import com.twinkle.twinklechat.viewmodel.ChatPageViewModel
import android.widget.Toast




class ChatPage : AppCompatActivity() {
    lateinit var dataBinding: ActivityChatPageBinding
    lateinit var viewModel: ChatPageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_chat_page)
        viewModel = ViewModelProvider(this)[ChatPageViewModel::class.java]
        dataBinding.apply {
            this.lifecycleOwner = this@ChatPage
            this.chatvm = viewModel
        }
        val chatlist = intent?.getBundleExtra("chatdata")?.getParcelable<ChatlistModel>("chatmodels")
        dataBinding.chatdata = chatlist
        //dataBinding.imageView4.clipToOutline = true
        /*val popupMenu = PopupMenu(this,dataBinding.imageView3)
        popupMenu.menuInflater.inflate(R.menu.chatmenu,popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.title) {
                "Block" -> {
                    item.title = "UnBlock"
                    dataBinding.constraintLayout.visibility = View.GONE
                    dataBinding.constraintLayout1.visibility = View.VISIBLE
                    popupMenu.dismiss()
                }
                "UnBlock"->{
                    item.title = "Block"
                    dataBinding.constraintLayout.visibility = View.VISIBLE
                    dataBinding.constraintLayout1.visibility = View.GONE
                    popupMenu.dismiss()
                }
                else ->{
                    popupMenu.dismiss()
                }
            }
            true
        }*/
        viewModel.backpressed.observe(this,{
            if (it){
                onBackPressed()
            }
        })
        viewModel.menupressed.observe(this,{
            if (it){
                //popupMenu.show()
            }
        })

        viewModel.sendpressed.observe(this,{
            if (it){

            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}