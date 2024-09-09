package com.app.sharedpreferencesecurity.datamanager


import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.app.sharedpreferencesecurity.util.Constant.KEY_USER_ID
import com.app.sharedpreferencesecurity.util.Constant.KEY_USER_NAME
import com.app.sharedpreferencesecurity.util.Constant.KEY_USER_PIN
import java.security.KeyStore

object EncryptedSharedPreference {



    private fun openPrefs(context: Context): SharedPreferences {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val keystore = KeyStore.getInstance("AndroidKeyStore")
        keystore.load(null)
        return EncryptedSharedPreferences.create(
            "TestPrefs_Encrypted",  // File name
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private fun setString(context: Context, key: String, value: String) {
        val editor = openPrefs(context).edit()
        editor?.let {
            it.putString(key, value)
            it.apply()
        }

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