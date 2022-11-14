package com.capstone.patech_android.data.api

import com.capstone.patech_android.data.response.HomeResponse
import retrofit2.http.GET

interface HomeService {

    @GET("/homepage")
    suspend fun getHome(): HomeResponse
}