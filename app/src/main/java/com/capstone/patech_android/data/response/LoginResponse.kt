package com.capstone.patech_android.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("pk")
    val id: Int?,
    val token: String?,
    val status: Int,
    val nickname: String?,
)
