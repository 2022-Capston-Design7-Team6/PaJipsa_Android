package com.capstone.patech_android.data.api

import com.capstone.patech_android.data.request.LoginRequest
import com.capstone.patech_android.data.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/users/login/")
    suspend fun postLogin(
        @Body body: LoginRequest
    ): LoginResponse
}