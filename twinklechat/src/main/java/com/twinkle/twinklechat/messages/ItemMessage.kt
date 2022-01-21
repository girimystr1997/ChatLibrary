package com.twinkle.twinklechat.messages

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.twinkle.twinklechat.R
import com.twinkle.twinklechat.databinding.ItemMessageBinding

class ItemMessage(context: Context, attributeSet: AttributeSet?) :
    ConstraintLayout(context, attributeSet) {

    var dataBinding: ItemMessageBinding =
        DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_message, this, true)

    init {
        getXmlAttributes(attributeSet)
    }

    private fun getXmlAttributes(attributeSet: AttributeSet?) {
        val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.ItemMessage)
        attributes.recycle()
    }
}