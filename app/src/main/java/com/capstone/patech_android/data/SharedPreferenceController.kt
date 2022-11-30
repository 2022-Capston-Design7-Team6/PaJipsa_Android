package com.capstone.patech_android.data

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceController {
    private const val STORAGE_KEY = "user_auth"
    private const val TOKEN = "AUTH_TOKEN"
    private const val NICKNAME = "NICKNAME"

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(
            "${context.packageName}.$STORAGE_KEY",
            Context.MODE_PRIVATE
        )
    }

    fun getToken(): String? {
        return sharedPreferences.getString(TOKEN, "")
    }

    fun setToken(token: String) {
        sharedPreferences.edit()
            .putString(TOKEN, "Token $token")
            .apply()
    }

    fun getNickName(): String? {
        return sharedPreferences.getString(NICKNAME, "")
    }

    fun setNickName(nickname: String) {
        sharedPreferences.edit()
            .putString(NICKNAME, nickname)
            .apply()
    }

    fun clearSdf(context: Context) {
        sharedPreferences.edit().clear().apply()
    }
}