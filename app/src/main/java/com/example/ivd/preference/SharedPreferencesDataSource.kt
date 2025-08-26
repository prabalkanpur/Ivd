package com.example.ivd.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPreferencesDataSource(context: Context) {

    private val PREF_NAME = "MyAppPref"
    private val KEY_USERNAME = "username"
    private val KEY_EMAIL = "email"
    private val KEY_PASSWORD = "email"

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveUsername(username: String) {
        sharedPref.edit { putString(KEY_USERNAME, username) }
    }

    fun getUsername(): String? {
        return sharedPref.getString(KEY_USERNAME, null)
    }

    fun saveEmail(email: String) {
        sharedPref.edit { putString(KEY_EMAIL, email) }
    }

    fun getEmail(): String? {
        return sharedPref.getString(KEY_EMAIL, null)
    }

    fun savePassword(email: String) {
        sharedPref.edit { putString(KEY_PASSWORD, email) }
    }

    fun getPassword(): String? {
        return sharedPref.getString(KEY_PASSWORD, null)
    }



    // Clear all data
    fun clear() {
        sharedPref.edit { clear() }
    }

}