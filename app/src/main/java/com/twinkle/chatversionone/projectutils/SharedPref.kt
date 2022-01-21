package com.twinkle.chatversionone.projectutils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class SharedPref {
    fun getBooleanValue(Tag: String?): Boolean {
        return myPrefs!!.getBoolean(Tag, false)
    }

    fun setBooleanValue(Tag: String?, token: Boolean) {
        prefsEditor!!.putBoolean(Tag, token)
        prefsEditor!!.apply()
    }

    fun setStoringValue(tag: String?, Value: String?) {
        prefsEditor!!.putString(tag, Value)
        prefsEditor!!.apply()
    }

    fun setStoringIntValue(tag: String?, Value: Int) {
        prefsEditor!!.putInt(tag, Value)
        prefsEditor!!.apply()
    }


    fun getStoringValue(tag: String?): String? {
        return myPrefs!!.getString(tag, "")
    }

    fun getStoringLangValue(tag: String?): String? {
        return myPrefs!!.getString(tag, "en")
    }

    fun getStoringIntValue(tag: String?): Int {
        return myPrefs!!.getInt(tag, 0)
    }


    fun clearAll() {
        prefsEditor!!.clear()
        prefsEditor!!.apply()
    }


    companion object {
        var myPrefs: SharedPreferences? = null
        var prefsEditor: SharedPreferences.Editor? = null
        var myObj: SharedPref? = null

        @SuppressLint("CommitPrefEdits")
        fun getInstance(ctx: Context?): SharedPref? {
            if (myObj == null) {
                myObj = SharedPref()
                myPrefs = ctx?.let { PreferenceManager.getDefaultSharedPreferences(it) }
                prefsEditor = myPrefs?.edit()
            }
            return myObj
        }
    }
}