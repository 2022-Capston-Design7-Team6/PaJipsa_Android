package com.capstone.patech_android.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val BASE_URL = "http://15.164.45.35"

    private val interceptor = Interceptor { chain ->
        with(chain) {
            val newRequest = request().newBuilder()
                //.addHeader("Authorization", "Token " + SharedPreferenceController.getJwtToken()!!)
                .addHeader("Authorization", "Token " + "46676f20ceb2c5cfa21c09e6cbed9cc6a80e991e")
                .build()
            proceed(newRequest)
        }
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val homeService: HomeService = retrofit.create(HomeService::class.java)
}