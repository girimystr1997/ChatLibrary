package com.twinkle.twinklechat.chatlist

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.twinkle.twinklechat.R
import com.twinkle.twinklechat.chatlist.model.ChatlistModel
import com.twinkle.twinklechat.databinding.ChatlistBinding

@SuppressLint("ViewConstructor")
class ChatList(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    var dataBinding:ChatlistBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.chatlist,
        this,true)
    init {
        getXmlAttributes(attrs)
    }

    private fun getXmlAttributes(attrs: AttributeSet?) {
        val attributes = context.obtainStyledAttributes(attrs,R.styleable.ChatList)
        dataBinding.imageView.clipToOutline = true
        dataBinding.textView.setTextColor(attributes.getColor(R.styleable.ChatList_titletextcolor,-1))
        dataBinding.textView2.setTextColor(attributes.getColor(R.styleable.ChatList_messagetextcolor,-1))
        dataBinding.timetext.setTextColor(attributes.getColor(R.styleable.ChatList_timetextcolor,-1))
        dataBinding.timetext.isVisible = attributes.getBoolean(R.styleable.ChatList_timevisiblity,true)
        dataBinding.textView4.setTextColor(attributes.getColor(R.styleable.ChatList_counttextcolor,-1))
        dataBinding.textView4.backgroundTintList = ColorStateList.valueOf(attributes.getColor(R.styleable.ChatList_countbackgroundcolor,-1))
        dataBinding.textView4.isVisible = attributes.getBoolean(R.styleable.ChatList_countvisibility,false)
        dataBinding.root.setBackgroundColor(attributes.getColor(R.styleable.ChatList_backgroundColor,-1))
        attributes.recycle()
    }

}