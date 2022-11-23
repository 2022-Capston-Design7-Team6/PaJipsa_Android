package com.capstone.patech_android.data.api

import com.capstone.patech_android.data.response.PlantDetailResponse
import com.capstone.patech_android.data.response.PlantListResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface PlantService {

    @GET("/plantpage/{plant}")
    suspend fun getPlantDetail(
        @Path("plant") plantId: Int,
    ): PlantDetailResponse

    @GET("/plantlist/")
    suspend fun getPlantList(): PlantListResponse

    @DELETE("/plants/{plant}/")
    suspend fun deletePlant(
        @Path("plant") plantId: Int
    )
}