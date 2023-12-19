package com.example.aplikasibelajar.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val prefNames = "xyz.halacity.elib"
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(prefNames, Context.MODE_PRIVATE)

    fun putString(keyName: String, value: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(keyName, value)
        editor.apply()
    }

    fun putBoolean(keyName: String, value: Boolean) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(keyName, value)
        editor.apply()
    }

    fun putIsInGame(value: Boolean) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("isInGame", value)
        editor.apply()
    }

    fun isInGame(): Boolean? {
        return sharedPreferences.getBoolean("isInGame", false)
    }

    fun getValueString(keyName: String): String? {
        return sharedPreferences.getString(keyName, null)
    }

    fun getValueBoolean(keyName: String): Boolean? {
        return sharedPreferences.getBoolean(keyName, false)
    }
}