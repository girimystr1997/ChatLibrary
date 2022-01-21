package com.twinkle.twinklechat.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChatPageViewModel:ViewModel() {

    val backpressed = MutableLiveData<Boolean>()
    val menupressed = MutableLiveData<Boolean>()
    val sendpressed = MutableLiveData<Boolean>()


    fun onBackClicked(){
        backpressed.value = true
    }
    fun onMenuClicked(){
        menupressed.value = true
    }
    fun onSendClicked(){
        sendpressed.value =true
    }
}