package com.capstone.patech_android.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceBuilder {
    private const val BASE_URL = "http://15.164.45.35"

    private val headerInterceptor = Interceptor { chain ->
        with(chain) {
            val newRequest = request().newBuilder()
                //.addHeader("Authorization", "Token " + SharedPreferenceController.getJwtToken()!!)
                .addHeader("Authorization", "Token " + "46676f20ceb2c5cfa21c09e6cbed9cc6a80e991e")
                .build()
            proceed(newRequest)
        }
    }

    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(headerInterceptor)
        .connectTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100, TimeUnit.SECONDS)
        .writeTimeout(100,TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val homeService: HomeService = retrofit.create(HomeService::class.java)
    val cameraService: CameraService = retrofit.create(CameraService::class.java)
    val authService: AuthService = retrofit.create(AuthService::class.java)
    val plantService: PlantService = retrofit.create(PlantService::class.java)
}