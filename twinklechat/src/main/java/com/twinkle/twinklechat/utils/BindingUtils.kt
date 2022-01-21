package com.twinkle.twinklechat.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.twinkle.twinklechat.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView?, url: String?) {
    Glide.with(view!!).load(url).into(view)
}