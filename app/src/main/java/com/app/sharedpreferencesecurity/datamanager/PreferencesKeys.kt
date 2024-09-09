package com.app.sharedpreferencesecurity.datamanager

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val PREF_ID = stringPreferencesKey("id")
    val PREF_NAME = stringPreferencesKey("name")
    val PREF_PIN = stringPreferencesKey("pin")


}