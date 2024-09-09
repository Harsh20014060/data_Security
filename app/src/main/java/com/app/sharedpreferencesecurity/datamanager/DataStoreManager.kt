package com.app.sharedpreferencesecurity.datamanager

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.app.sharedpreferencesecurity.model.UserDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

object DataStoreManager {


    private lateinit var dataStore: DataStore<Preferences>

    fun init(datastore: DataStore<Preferences>) {
        dataStore = datastore
    }

    private fun getString(key: Preferences.Key<String>, defaultValue: String = ""): Flow<String> {
        return dataStore.data.map { preferences -> preferences[key] ?: defaultValue }
    }

    suspend fun setUserData(userData: UserDataModel) {
        setUserID(userData.id ?: "")
        setName(userData.name ?: "")
        setPin(userData.pin ?: "")

    }

    suspend fun getUserData(): UserDataModel {
        return UserDataModel(
            id = getUserID(),
            name = getName(),
            pin = getPin(),
        )

    }

    private suspend fun setUserID(value: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.PREF_ID] = value
        }

    }

    private suspend fun getUserID(defaultValue: String = ""): String {
        return getString(PreferencesKeys.PREF_ID).firstOrNull() ?: defaultValue

    }

    private suspend fun setName(value: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.PREF_NAME] = value
        }

    }

    private suspend fun getName(defaultValue: String = ""): String {
        return getString(PreferencesKeys.PREF_NAME).firstOrNull() ?: defaultValue
    }

    private suspend fun setPin(value: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.PREF_PIN] = value
        }
    }

    private suspend fun getPin(defaultValue: String = ""): String {
        return getString(PreferencesKeys.PREF_PIN).firstOrNull() ?: defaultValue
    }


    suspend fun deleteData() {
        dataStore.edit {
            it.clear()
        }
    }


}