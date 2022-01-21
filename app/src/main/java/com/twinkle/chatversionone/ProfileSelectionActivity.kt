package com.twinkle.chatversionone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.twinkle.chatversionone.databinding.ActivityProfileSelectionBinding
import com.twinkle.chatversionone.projectutils.SharedPref
import com.twinkle.twinklechat.chatlist.model.ProfileDataModel

class ProfileSelectionActivity : AppCompatActivity() {
    lateinit var dataBinding: ActivityProfileSelectionBinding
    var sharedPref = SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_profile_selection)

        dataBinding.lifecycleOwner = this
        sharedPref.getInstance(this)
        dataBinding.button.setOnClickListener {
            val profileDataModel = ProfileDataModel(
                "Kili",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQKpebiXZUYDI2ntXXFgdCM1OmK54zWl3H4GA&usqp=CAU",
                "mainUid",
                "9597937345",
                System.currentTimeMillis().toString()
            )
            startActivity(Intent(this,MainActivity::class.java).putExtra("profileData",profileDataModel))
            finish()
        }
        dataBinding.button2.setOnClickListener {
            val profileDataModel = ProfileDataModel(
                "Kiki",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQKpebiXZUYDI2ntXXFgdCM1OmK54zWl3H4GA&usqp=CAU",
                "secondUid",
                "9025049235",
                System.currentTimeMillis().toString()
            )
            startActivity(Intent(this,MainActivity::class.java).putExtra("profileData",profileDataModel))
            finish()
        }
    }
}