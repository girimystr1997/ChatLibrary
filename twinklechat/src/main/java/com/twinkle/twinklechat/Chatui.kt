package com.twinkle.twinklechat


import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.ContextMenu
import android.view.LayoutInflater
import android.widget.PopupMenu
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.twinkle.twinklechat.databinding.ActivityChatPageBinding

@SuppressLint("ViewConstructor")
class Chatui(context: Context,attributeSet: AttributeSet?):ConstraintLayout(context,attributeSet) {
    val dataBinding:ActivityChatPageBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.activity_chat_page
    ,this,true)
    init {
        xmlAttributes(attributeSet)
    }



    private fun xmlAttributes(attributeSet: AttributeSet?) {
        val attributes = context.obtainStyledAttributes(attributeSet,R.styleable.Chatui)
        Glide.with(context).load(attributes.getString(R.styleable.Chatui_profileimageurl)).into(dataBinding.profilepic)
        dataBinding.profilepic.clipToOutline = true
        dataBinding.backicon.isVisible = attributes.getBoolean(R.styleable.Chatui_backiconvisibility,true)
        dataBinding.backicon.imageTintList = ColorStateList.valueOf(attributes.getColor(R.styleable.Chatui_backiconcolor,-1))
        dataBinding.menuicon.isVisible = attributes.getBoolean(R.styleable.Chatui_menuiconvisibility,true)
        attributes.recycle()
    }
}