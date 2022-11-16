package com.capstone.patech_android.data.api

import com.capstone.patech_android.data.response.HomeResponse
import com.capstone.patech_android.data.response.PatechResponse
import retrofit2.http.GET

interface HomeService {

    @GET("/homepage/")
    suspend fun getHome(): HomeResponse

    @GET("/rank/")
    suspend fun getPatechInfo(): PatechResponse
}