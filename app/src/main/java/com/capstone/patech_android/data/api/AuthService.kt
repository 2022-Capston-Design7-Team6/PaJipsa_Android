package com.capstone.patech_android.data.api

import com.capstone.patech_android.data.request.LoginRequest
import com.capstone.patech_android.data.request.NickNamePut
import com.capstone.patech_android.data.request.RegisterRequest
import com.capstone.patech_android.data.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface AuthService {

    @POST("/users/login/")
    suspend fun postLogin(
        @Body body: LoginRequest
    ): LoginResponse

    @PUT("/users/chgnickname/")
    suspend fun putNickName(
        @Body body: NickNamePut
    ): Response<Unit>

    @POST("/users/register/")
    suspend fun postRegister(
        @Body body: RegisterRequest
    ): LoginResponse
}