package com.capstone.patech_android.data.api

import com.capstone.patech_android.data.response.PlantDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PlantService {

    @GET("/plantpage/{plant}")
    suspend fun getPlantDetail(
        @Path("plant") plantId: Int,
    ): PlantDetailResponse
}