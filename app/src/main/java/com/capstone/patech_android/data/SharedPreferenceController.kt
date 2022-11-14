package com.capstone.patech_android.data

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceController {
    private const val STORAGE_KEY = "user_auth"
    private const val TOKEN = "JWT_TOKEN"

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(
            "${context.packageName}.$STORAGE_KEY",
            Context.MODE_PRIVATE
        )
    }

    fun getJwtToken(): String? {
        return sharedPreferences.getString(TOKEN, "")
    }

    fun setJwtToken(jwt: String) {
        sharedPreferences.edit()
            .putString(TOKEN, jwt)
            .apply()
    }

    fun clearJwtToken(context: Context) {
        sharedPreferences.edit().clear().apply()
    }
}