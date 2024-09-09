package com.app.sharedpreferencesecurity

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.preferencesDataStore
import com.app.sharedpreferencesecurity.databinding.ActivityMainBinding
import com.app.sharedpreferencesecurity.datamanager.DataStoreManager
import com.app.sharedpreferencesecurity.datamanager.EncryptedSharedPreference
import com.app.sharedpreferencesecurity.datamanager.PreferenceUtils
import com.app.sharedpreferencesecurity.model.UserDataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val userPreferenceName = "user_preferences"

    private val Context.dataStore by preferencesDataStore(
        name = userPreferenceName
    )
    private val userData by lazy {
        UserDataModel(
            id = "1", name = "Agent", pin = "1234"
        )
    }

    private lateinit var binder: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binder.root)



        binder.btnSavePreference.setOnClickListener {
            PreferenceUtils.setUserID(this, userData.id ?: "")
            PreferenceUtils.setUserName(this, userData.name ?: "")
            PreferenceUtils.setUserPin(this, userData.pin ?: "")
        }

        binder.btnSaveDataStore.setOnClickListener {
            DataStoreManager.init(dataStore)

            CoroutineScope(Dispatchers.IO).launch {
                DataStoreManager.setUserData(
                    UserDataModel(
                        id = userData.id ?: "",
                        name = userData.name ?: "",
                        pin = userData.pin ?: ""
                    )
                )
            }
        }
        binder.btnSaveEncryptedSharedPreference.setOnClickListener {
            EncryptedSharedPreference.setUserID(this, userData.id ?: "")
            EncryptedSharedPreference.setUserName(this, userData.name ?: "")
            EncryptedSharedPreference.setUserPin(this, userData.pin ?: "")

        }


    }
}