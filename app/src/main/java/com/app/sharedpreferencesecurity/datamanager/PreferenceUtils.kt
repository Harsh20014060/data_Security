package com.app.sharedpreferencesecurity.datamanager

import android.content.Context
import android.content.SharedPreferences
import com.app.sharedpreferencesecurity.util.Constant.KEY_USER_ID
import com.app.sharedpreferencesecurity.util.Constant.KEY_USER_NAME
import com.app.sharedpreferencesecurity.util.Constant.KEY_USER_PIN

object PreferenceUtils {

    private var sharedPreferences: SharedPreferences? = null

    private fun openPrefs(context: Context) {
        sharedPreferences = context.getSharedPreferences(
            "TestPrefs",
            Context.MODE_PRIVATE
        )
    }

    private fun setString(context: Context, key: String, value: String) {
        openPrefs(context)
        val editor: SharedPreferences.Editor? = sharedPreferences!!.edit()
        editor!!.putString(key, value)
        editor.apply()
    }

    fun setUserID(context: Context, value: String) {
        setString(
            context = context,
            KEY_USER_ID,
            value
        )
    }

    fun setUserName(context: Context, value: String) {
        setString(
            context = context,
            KEY_USER_NAME,
            value
        )
    }

    fun setUserPin(context: Context, value: String) {
        setString(
            context = context,
            KEY_USER_PIN,
            value
        )
    }


}